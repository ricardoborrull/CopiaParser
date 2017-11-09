public class Parser_Persona {

	public static void main(String[] args) {
				
		Parser parser=new Parser();
		parser.parseFicheroXml("src\\Personas.xml");
		parser.parseDocument();
		parser.print();
		
	}

}