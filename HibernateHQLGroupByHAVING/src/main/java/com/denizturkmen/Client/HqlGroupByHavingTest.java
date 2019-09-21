package com.denizturkmen.Client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.denizturkmen.Util.HibernateUtil;


public class HqlGroupByHavingTest {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String HQL = "SELECT dept.departmentName,SUM(emp.salary) FROM Department dept"
					+ " LEFT JOIN dept.employees emp GROUP BY dept"
					+ " HAVING SUM(emp.salary)>20000";
			
			Query<Object[]> query = session.createQuery(HQL, Object[].class);
			List<Object[]> list = query.list();
			for (Object[] objects : list) {
				String departmentName=(String)objects[0];
				Double totalSalByDept = (Double)objects[1];
				System.out.println("Department Name:"+departmentName);
				System.out.println("Toplam Departmandaki Maası 2000 den fazla:"+totalSalByDept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();
		}
	}

}