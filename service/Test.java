public class Test {
	public static void main(String[] args){
		SecureService service = new SecureService();
		service.sendMsg(":chatLogin", "davide");
		service.sendMsg(":listUsers", "davide");
		System.out.println(service.reciveMsg("davide"));
	}
}
