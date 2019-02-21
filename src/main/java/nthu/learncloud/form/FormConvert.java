package nthu.learncloud.form;

public interface FormConvert<S,T> {
    T convert(S s);
}
