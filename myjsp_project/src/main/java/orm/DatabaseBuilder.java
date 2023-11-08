package orm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseBuilder {

	private static SqlSessionFactory factory;
	private static final String config = "orm/mybatisConfig.xml";
	
	static { //초기화블럭
		try {
			factory = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsReader(config));
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() { //공유될 수 있도록     (~DAOImpl에서 불러다 씀)
		return factory;
	}
	
}
