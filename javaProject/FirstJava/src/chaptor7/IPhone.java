package chaptor7;

public class IPhone extends Phone{
	
	public IPhone(String phoneNumber) {
		super(phoneNumber);
	}

	@Override
	void call() {
		// TODO Auto-generated method stub
		super.call();
		System.out.println("아이폰으로 통화");
	}
	
	

}
