public class Test {
  protected static String username = "davide";
  protected static String password = "";

  public static void main(String[] args) throws Exception {
    SecureService toTest = new SecureService();
    toTest.login("andrea");
    toTest.login(username);
    toTest.send(": listUsers", username);
    System.out.println(toTest.recive(username));
  }
}
