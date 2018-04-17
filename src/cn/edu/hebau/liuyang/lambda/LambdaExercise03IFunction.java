package cn.edu.hebau.liuyang.lambda;

@FunctionalInterface
public interface LambdaExercise03IFunction<T, R> {
	public R process(T t1, T t2);
}
