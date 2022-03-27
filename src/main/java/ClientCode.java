public class ClientCode {
    public static void main(String[] args) {

        Hibernate hibernate = new Hibernate();
        hibernate.deleteCategoryOfPasswords("Test");
        hibernate.closeFactory();

    }
}
