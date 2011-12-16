package ma.norsys.technomaker.rest.util;

public interface Constants {
	public static final String XML_FILE_PATH_TEST = "\\database_test.xml";
	public static final String XML_FILE_PATH_MAIN = "\\database_main.xml";

	// Soit la structure du fichier XML est la suivante
	// <queues>
	public static final String XML_FILE_ROOT = "queues";
	// ________<queue>
	public static final String XML_FILE_ELEMENT_QUEUE = "queue";
	// ________________<idQueue>
	public static final String XML_FILE_ELEMENT_ID_QUEUE = "idQueue";
	// ________________</idQueue>
	// ________________<messages>
	public static final String XML_FILE_ELEMENT_MESSAGES = "messages";
	// ________________________<message>
	public static final String XML_FILE_ELEMENT_MESSAGE = "message";
	// ________________________________<idMessage>
	public static final String XML_FILE_ELEMENT_ID_MESSAGE = "idMessage";
	// ________________________________</idMessage>
	// ________________________________<objet>
	public static final String XML_FILE_ELEMENT_OBJET = "objet";
	// ________________________________</objet>
	// ________________________________<contenu>
	public static final String XML_FILE_ELEMENT_CONTENU = "contenu";
	// ________________________________</contenu>
	// ________________________</message>
	// ________________________...
	// ________________</messages>
	// ________</queue>
	// ________...
	// </queues>
}
