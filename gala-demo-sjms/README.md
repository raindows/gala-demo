原型模式（Prototype）
该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。

浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。

 /* 浅复制 */  
    public Object clone() throws CloneNotSupportedException {  
        Prototype proto = (Prototype) super.clone();  
        return proto;  
    }  
  
    /* 深复制 */  
    public Object deepClone() throws IOException, ClassNotFoundException {  
  
        /* 写入当前对象的二进制流 */  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(this);  
  
        /* 读出二进制流产生的新对象 */  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis);  
        return ois.readObject();  
    }  
    
===============  
 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，
 目的是消除由于接口不匹配所造成的类的兼容性问题。
 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。

===========
抽象方法
抽象方法是用abstract修饰的方法，只能声明不能实现，抽象方法必须被声明在抽象类里（反过来，抽象类里不一定要有抽象方法），抽象方法的的作用就是强制子类实现该抽象方法（如果子类不是抽象类的话）。
实例方法
可以用对象引用调用的方法都可以称作实例方法，实例方法必须在对象实例化之后，通过对象引用来调用。
静态方法
即不需要对象实例就可以调用的方法，也叫做类方法，可以通过类名直接调用。
虚方法
可以被覆写的方法都可以称作虚方法，因此虚方法并不需要做特殊的声明，也可以理解为除了用static、final、private修饰之外的所有方法都是虚方法。
抽象类和接口的区别
1、抽象类可以有方法体，接口必须是方法声明。 
2、可以把接口看成是更纯粹的抽象类。 
3、他们都不可以被实例化，但可以完好的使用多态（这是相同点）。

    
    