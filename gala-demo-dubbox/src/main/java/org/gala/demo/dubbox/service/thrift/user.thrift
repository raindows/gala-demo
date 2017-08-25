namespace java cn.thrift.idl // 定义命名空间

// include "test.thrift" // 包含外部的接口文件

/* 定义一个Person类 */
struct UserModel2
{
    1: string id, 
    2: string name,
    3: optional i64 age, 
}


/* 服务接口 */
service UserService2
{
    UserModel2 getUser(1:string id),
	
	bool saveUser(1:UserModel2 model),
}