package hellojpa.dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

// JPQL에 기본적으로 제공하는 함수가 아닌경우 개별적으로 등록해주어야 한다.
// 이미 등록되어 있는 함수를 참고해서 생성하면 된다.
// persistance.xml에 dialect에 등록해주어야한다.
public class MyH2Dialect extends H2Dialect {

    public MyH2Dialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
