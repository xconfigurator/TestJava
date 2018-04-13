package cn.edu.hebau.liuyang;

import java.io.Serializable;

public interface TestInterface extends Serializable, Cloneable {
	// 接口之间可以多继承
	// JDK7 及以前没有问题。
	// JDK8 引入静态方法和默认方法后呢……，会不会有C++中多继承的菱形问题？
}
