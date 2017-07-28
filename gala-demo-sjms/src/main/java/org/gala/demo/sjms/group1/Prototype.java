package org.gala.demo.sjms.group1;

/**
 * @author yuan.li
 * 
 *         原型模式（Prototype） 该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。
 *
 */
public class Prototype implements Cloneable {

	public Object clone() throws CloneNotSupportedException {
		Prototype proto = (Prototype) super.clone();
		return proto;
	}

}
