
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        SistemaPDI sistema = new SistemaPDI();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("SISTEMA DE PDI");
        System.out.println("1 - Login como Administrador");
        System.out.println("2 - Login como Usuario");
        System.out.print("Escolha: ");
        
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            if (opcao < 1 || opcao > 2) {
                throw new IllegalArgumentException("Opcao deve ser 1 ou 2");
            }
            
            TipoUsuario tipo = (opcao == 1) ? TipoUsuario.ADMINISTRADOR : TipoUsuario.USUARIO;
            sistema.menu(tipo, scanner);
        } catch (InputMismatchException e) {
            System.out.println("ERRO: Entrada invalida! Digite um numero inteiro (1 ou 2).");
            scanner.nextLine(); 
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO inesperado: " + e.getMessage());
            System.out.println("Tipo: " + e.getClass().getSimpleName());
        } finally {
            scanner.close();
        }
    }
}