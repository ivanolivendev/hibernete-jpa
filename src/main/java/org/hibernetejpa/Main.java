package org.hibernetejpa;

// IMPORTANTE: Adicione estes imports!
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernetejpa.enums.EstadoCivil;
import org.hibernetejpa.models.Endereco;
import org.hibernetejpa.models.Usuario;
import org.hibernetejpa.models.Veiculo;

import java.time.LocalDate;
import java.util.Date;
// Importe sua classe de entidade (exemplo)
// import org.hibernetejpa.model.Usuario;

public class Main {
    public static void main(String[] args) {

//        Endereco endIvan = new Endereco();
//        endIvan.setBairro("marambaia");
//        endIvan.setCep("66615515");
//        endIvan.setRua("Alameda Agua Cristal");
//
//        Endereco endIvanComercial = new Endereco();
//        endIvanComercial.setBairro("marambaia");
//        endIvanComercial.setCep("66615515");
//        endIvanComercial.setRua("Alameda Agua Cristal");

        Veiculo fusca = new Veiculo();
        fusca.setAno(1999);
        fusca.setModelo("Fusca");


        Veiculo brasilia = new Veiculo();
        brasilia.setModelo("brasilia");
        brasilia.setAno(1999);




         Usuario ivan = new Usuario();
         ivan.setNome("Ivan");
         ivan.setDataNasciemnto(LocalDate.now());
         ivan.setEstadoCivil(EstadoCivil.CASADO);
         ivan.setIdade(36);
         ivan.getVeiculos().add(fusca);
         ivan.getVeiculos().add(brasilia);

         //ivan.setVeiculo(fusca);
//         ivan.getEnderecos().add(endIvanComercial);
//         ivan.getEnderecos().add(endIvan);

        fusca.setUsuario(ivan);
        brasilia.setUsuario(ivan);




        // 1. Cria a SessionFactory (O objeto pesado que gerencia o banco)
        // O try() aqui garante que o factory seja fechado ao final do programa
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {

            // 2. Abre uma sessão (A "conversa" atual com o banco)
            try (Session session = factory.openSession()) {

                // 3. Iniciar uma transação (Obrigatório para salvar/alterar dados)
                Transaction t = session.beginTransaction();

                System.out.println("Conexão estabelecida com sucesso no PostgreSQL!");

                /*
                // EXEMPLO: Salvando um dado de verdade
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome("Fulano de Tal");

                session.persist(novoUsuario); // Salva o objeto
                */

                //ession.persist(fusca);
                session.persist(ivan);

                // 4. Finaliza a transação
                t.commit();

                Usuario usuarioBanco = session.find(Usuario.class, ivan.getId());


                System.out.println("Transação concluída!");
                // ABRA UMA NOVA SESSÃO
//                try (Session session2 = factory.openSession()) {
//                    // Busca o usuário que já existe no banco
//                    Usuario usuarioDoBanco = session2.find(Usuario.class, ivan.getId());
//
//                    session2.close(); // FECHA ANTES DE ACESSAR A LISTA
//
//                    // AGORA O ERRO VAI APARECER!
//                    // Pois 'usuarioDoBanco' veio do banco com a lista "preguiçosa" (Lazy)
//                    System.out.println("Bairro: " + usuarioDoBanco.getEnderecos().get(0).getBairro());
//                }

            } catch (Exception e) {
                System.err.println("Erro na sessão: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Erro ao configurar o Hibernate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}