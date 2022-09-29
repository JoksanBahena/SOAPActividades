import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.security.SecureRandom;
import java.util.Random;

@WebService(name = "Service", targetNamespace = "utez")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Service {
    @WebMethod(operationName = "responseMessage")
    public String responseMessage(@WebParam(name = "message") String message) {
        return "El mensaje recibido fue " + message;

    }

    @WebMethod(operationName = "adivinarNumero")
    public String responseMessage1(@WebParam(name = "message") int num) {
        Random numeroRandom = new Random();
        int numero = numeroRandom.nextInt(10);

        System.out.println(numero);

        if (numero == num) {
            return "Le atinaste!";

        }else {
            return "Intenta de nuevo";
        }
    }

    @WebMethod(operationName = "consonantes")
    public String responseMessage2(@WebParam(name = "message") String frase) {

        return frase.replaceAll("[aeiou]", "");
    }

    @WebMethod(operationName = "rfc")
    public String responseMessage3(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellidoP") String apellidoP,
                                   @WebParam(name = "apellidoM") String apellidoM, @WebParam(name = "fechaNac") String fechaNac) {

        String letraNombre = nombre.substring(0,1);
        String letraApellido1 = apellidoP.substring(0,2);
        String letraApellido2 = apellidoM.substring(0,1);
        String numAño = fechaNac.substring(8,10);
        String numMes = fechaNac.substring(3,5);
        String numDia = fechaNac.substring(0,2);

        String randomXD = generateRandomString(3);

        String rfcT = letraApellido1+letraApellido2+letraNombre+numAño+numMes+numDia+randomXD;

        return rfcT.toUpperCase();
    }

    public static void main(String[] args) {
        //System.out.println("Iniciando servidor...");
        System.out.println("Initializing service...");
        Endpoint.publish("http://localhost:8080/Service", new Service());
        //System.out.println("Esperando respuesta...");
        System.out.println("Waiting requests...");
    }

    public static String generateRandomString(int length) {

        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }
}
