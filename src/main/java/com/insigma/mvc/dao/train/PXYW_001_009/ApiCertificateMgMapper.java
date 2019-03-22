package com.insigma.mvc.dao.train.PXYW_001_009;

import java.util.List;

import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Student;

public interface ApiCertificateMgMapper {

	List<Hb67> getClasssList(Hb67 hb67);

	List<Student> getStuList(Student stu);
	
	List<Student> getStuList_df(Student stu);

	int saveDate(Student stu);

	void update(Student stu);

	void updateState(String chb067);

	int submit(Hb67 hb67);

	int undo(Hb67 hb67);

	Student getHc67(String chc067);

	void doSet(Student stu);

	void deleteCertificate(Student stu);

	Hb67 getHb67ById(String chb067);

	Hb67 getHb67ById_df(String chb067);

	Hb67 getHb67ById_sx(String chb067);

}
