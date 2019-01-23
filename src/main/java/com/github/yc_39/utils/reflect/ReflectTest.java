package com.github.yc_39.utils.reflect;

import java.lang.reflect.Constructor;

/**
 * 反射
 * @author yc39
 *
 */
public class ReflectTest {

	public ReflectTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
		Class<?> clazz1 = null;
		Class<?> clazz2 = null;
		Class<?> clazz3 = null;
		
		//实例化Class类对象的几种方式
		clazz1 = Class.forName("com.reflect.User");
		clazz2 = new User().getClass();
		clazz3 = User.class;
		
		System.out.println("[类名]" + clazz1.getName());
		System.out.println("[类名]" + clazz2.getName());
		System.out.println("[类名]" + clazz3.getName());
		
		//获取父类
		Class<?> parentClazz = clazz1.getSuperclass();
		System.out.println("[父类名]" + parentClazz.getName());
		
		//获取所有接口
		Class<?> interfaces[] = clazz1.getInterfaces();
		System.out.println("[实现的接口]");
		for(Class<?> _interface : interfaces){
			System.out.println(_interface.getName());
		}
		
		//通过反射机制实例化类对象
		//第一种方法，实例化默认构造方法
		User user = (User) clazz1.newInstance();
		user.setAge(25);
		user.setName("yc");
		user.setId(39);
		System.out.println(user); //结果 User [id]39,[name]yc,[age]25
		
		//第二种方法，取得全部构造函数，使用构造函数赋值
		Constructor<?> constructors[] = clazz1.getConstructors();
		//查看每个构造方法需要的参数
		for(int i=0; i<constructors.length; i++){
			Class<?> clazzs[] = constructors[i].getParameterTypes();
			System.out.print("constructors[" + i + "] (");
			for(int j=0; j<clazzs.length; j++){
				if(j==clazzs.length-1)System.out.print(clazzs[j].getName());
				else System.out.print(clazzs[j].getName() + ", ");
			}
			System.out.println(")");
		}
		
		user = (User) constructors[0].newInstance("yc", 39);
		System.out.println(user); //结果 User [id]0,[name]yc,[age]39
		user = (User) constructors[1].newInstance("yc");
		System.out.println(user); //结果 User [id]0,[name]yc,[age]0
	}

}
