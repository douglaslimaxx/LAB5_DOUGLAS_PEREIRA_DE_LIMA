package modelos;

public class Main {
	private static Main EasyAccept;

	public static void main(String[] args) {
		args = new String[] { "modelos.Facade", "acceptance_test/us1_test.txt",
				"acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt" };
		EasyAccept.main(args);
	}

}