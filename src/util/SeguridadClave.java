package util;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class SeguridadClave {
    private static final int ITERACIONES=120000;
    private SeguridadClave(){}
    public static String crear(String clave) {
        try {
            byte[] sal=new byte[16];new SecureRandom().nextBytes(sal);
            byte[] calculado=derivar(clave,sal);
            return Base64.getEncoder().encodeToString(sal)+":"+Base64.getEncoder().encodeToString(calculado);
        }catch(Exception ex){throw new IllegalStateException("No se puede proteger la contraseña",ex);}
    }
    private static byte[] derivar(String clave,byte[] sal) throws Exception {
        PBEKeySpec spec=new PBEKeySpec(clave.toCharArray(),sal,ITERACIONES,256);
        try{return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}
        finally{spec.clearPassword();}
    }
    public static boolean verificar(String clave,String almacenado){
        if(almacenado==null)return false;
        try{
            String[] partes=almacenado.split(":",-1);if(partes.length!=2)return false;
            byte[] sal=Base64.getDecoder().decode(partes[0]);
            byte[] esperado=Base64.getDecoder().decode(partes[1]);
            byte[] real=derivar(clave,sal);
            return java.security.MessageDigest.isEqual(esperado,real);
        }catch(Exception ex){return false;}
    }
}
