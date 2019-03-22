package com.insigma.mvc.controller.app.APP_002_001;

import com.github.pagehelper.PageInfo;
import com.insigma.common.fastdfs.Fastdfs;
import com.insigma.common.listener.AppConfig;
import com.insigma.common.util.VideoThumbTaker;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.*;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.app.APP_002_001.ApiSuiZhouTrainingService;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/suizhou")
public class ApiSuiZhouTrainingController extends MvcHelper {

    private static String fileModule = AppConfig.getProperties("fileModule");

    @Autowired
    private ApiSuiZhouTrainingService trainingService;
	
    @Autowired
    private ApiFileUploadService fileLoadService;

    @RequestMapping("/getLoginPerson")
    public AjaxReturnMsg getLoginPerson(Hb61 hb61) {
        Hb61 result = trainingService.getLoginPerson(hb61);
        if (result == null) {
            return this.error("用户名或密码错误");
        } else {
            return this.success(result);
        }
    }

    @RequestMapping("/updateLoginInfo")
    public AjaxReturnMsg updateLoginInfo(Hb61 hb61){
        trainingService.updateLoginInfo(hb61);
        return this.success("修改成功");
    }

    @RequestMapping("/quitSystem")
    public AjaxReturnMsg quitSystem(Hb61 hb61) {
        trainingService.quitSystem(hb61.getAac002());
        return this.success("已退出");
    }

    @RequestMapping("/getTeacher")
    public AjaxReturnMsg getTeacher(Hb61 hb61) {
        Hb61 result = trainingService.getTeacher(hb61.getChb061());
        if (result == null) {
            return this.error("用户不存在");
        } else {
            result.setPhoto_url(fileModule + result.getPhoto_url());
            return this.success(result);
        }
    }

    @RequestMapping("/getCurrentClass")
    public AjaxReturnMsg getCurrentClass(Hb61 hb61) {
        Hb68 result = trainingService.getCurrentClass(hb61.getAac002());
        if (result == null) {
            return this.error("当前没有课程");
        } else {
            return this.success(result);
        }
    }

    @RequestMapping("/getCurrentCourse")
    public AjaxReturnMsg getCurrentCourse(Hb61 hb61) {
        Hb69 result = trainingService.getCurrentCourse(hb61.getAac002());
        if (result == null) {
            return this.error("当前没有课程");
        } else {
            return this.success(result);
        }
    }

    @RequestMapping("/getNextCourse")
    public AjaxReturnMsg getNextCourse(Hb61 hb61) {
        Hb69 result = trainingService.getNextCourse(hb61.getAac002());
        if (result == null) {
            return this.error("下节没有课程");
        } else {
            return this.success(result);
        }
    }

    @RequestMapping("/uploadCourseInfo")
    public AjaxReturnMsg uploadCourseInfo(String chb069, MultipartFile file, String file_bus_description) {
        try {
            //保存图片到文件服务器，同时保存图片记录
            SFileRecord sFileRecord = fileLoadService.uploadFile(file, Param.TRAIN_APP_ATTACHMENT, file.getOriginalFilename(), chb069, file_bus_description);

            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            if("mp4".equals(suffix)){
                File localFile = new File("temp.mp4");
                System.out.println(localFile.getAbsolutePath());
                FileUtils.copyInputStreamToFile(file.getInputStream(),localFile);
                File localImage = new File(file.getOriginalFilename()+".jpg");
                System.out.println(localImage.getAbsolutePath());
                VideoThumbTaker.fetchFrame(localFile.getAbsolutePath(),localImage.getAbsolutePath());

                Fastdfs fastdfs=Fastdfs.getInstance();
                String result=fastdfs.uploadFile(localImage.getAbsolutePath());
                sFileRecord.setFile_bus_name(result);
                trainingService.updateSFileRecord(sFileRecord);
                localFile.delete();
                localImage.delete();
            }

            return this.success(fileModule + sFileRecord.getFile_rel_path());
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }

    @RequestMapping("/uploadVideoRecord")
    public AjaxReturnMsg uploadVideoRecord(String chb069, MultipartFile file, String file_bus_description) {
        try {
            //保存图片到文件服务器，同时保存图片记录
            SFileRecord sFileRecord = fileLoadService.uploadFile(file, Param.TRAIN_APP_VIDEO_RECORD, file.getOriginalFilename(), chb069, file_bus_description);

            return this.success(fileModule + sFileRecord.getFile_rel_path());
        } catch (Exception e) {
            return this.error(e.getMessage());
        }
    }

    @RequestMapping("/updatePassword")
    public AjaxReturnMsg updatePassword(Hb61 hb61) {
        Hb61 result = trainingService.getLoginPerson(hb61);
        if (result == null) {
            return this.error("原密码错误");
        } else {
            trainingService.updatePassword(hb61);
            return this.success("修改密码成功");
        }
    }

    @RequestMapping("/getCurrentClassDetail")
    public AjaxReturnMsg getCurrentClassDetail(Hb68 hb68) {
        Hb68 result = trainingService.getCurrentClassDetail(hb68.getChb068());
        return this.success(result);
    }

    @RequestMapping("/getCurrentClassCourseList")
    public AjaxReturnMsg getCurrentClassCourseList(Hb69 hb69) {
        PageInfo<Hb69> pageInfo = trainingService.getCurrentClassCourseList(hb69);
        return this.success(pageInfo);
    }

    @RequestMapping("/getCurrentCourseDetail")
    public AjaxReturnMsg getCurrentCourseDetail(Hb69 hb69) {
        Hb69DTO result = trainingService.getCurrentCourseDetail(hb69.getChb069());
        return this.success(result);
    }

    @RequestMapping("/getTrainingNum")
    public AjaxReturnMsg getTrainingNum(Hb68 hb68) {
        Integer result = trainingService.getTrainingNum(hb68.getChb068());
        return this.success(result);
    }

    @RequestMapping("/getTrainingVideoInfo")
    public AjaxReturnMsg getTrainingVideoInfo(Hb69 hb69) {
        List<Hb69> result = trainingService.getTrainingVideoInfo(hb69.getChb069());
        for (Hb69 hb691 : result) {
            hb691.setChc001(fileModule + hb691.getChc001());
            hb691.setAac003(fileModule + hb691.getAac003());
            hb691.setFile_bus_name(fileModule + hb691.getFile_bus_name());
        }
        return this.success(result);
    }

    @RequestMapping("/getStudents")
    public AjaxReturnMsg getStudents(Hb69 hb69) {
        List<Hc61Dto> result = trainingService.getStudents(hb69.getChb069());
        for (Hc61Dto hc61Dto : result) {
            hc61Dto.setPhoto_url(fileModule + hc61Dto.getPhoto_url());
        }
        return this.success(result);


    }

    @RequestMapping("/getStudentInfoList")
    public AjaxReturnMsg getStudentInfoList(Hb69 hb69) {
        List<Hc61Dto> result = trainingService.getStudentInfoList(hb69.getChb069());
        for (Hc61Dto hc61Dto : result) {
            hc61Dto.setPhoto_url(fileModule + hc61Dto.getPhoto_url());
        }
        return this.success(result);
    }

    @RequestMapping("/getPastClassList")
    public AjaxReturnMsg getPastClassList(Hb69 hb69) {
        List<Hb68> result = trainingService.getPastClassList(hb69.getAac002());
        return this.success(result);
    }

    @RequestMapping("/getVideoRecordInfo")
    public AjaxReturnMsg getVideoRecordInfo(Hc82 hc82){
        String aac002 = hc82.getAac002();
        Hc82 result = trainingService.getVideoRecordInfo(aac002);
        if(result == null){
            return this.error("没有监管记录");
        }else {
            return this.success(result);
        }
    }

    @RequestMapping("/updateVideoRecordInfo")
    public AjaxReturnMsg updateVideoRecordInfo(Hc82 hc82){
        String id = hc82.getId();
        trainingService.updateVideoRecordInfo(id);
        return this.success("修改成功");
    }
}
