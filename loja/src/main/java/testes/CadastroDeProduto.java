package testes;

import dao.CategoriaDao;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Produto;
import util.JpaUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1L);
        System.out.println(p.getNome());
        List<Produto> produtos = produtoDao.buscarTodos();
        produtos.forEach(p2 -> System.out.println(p2.getNome()));
        List<Produto> samsungGalaxy = produtoDao.buscarPorNome("SAMSUNG GALAXY");
        samsungGalaxy.forEach(p3 -> System.out.println(p3.getCategoria().getNome()));
        List<Produto> produtoPorCategoria = produtoDao.buscarPorNomeDaCategoria("Celulares");
        produtoPorCategoria.forEach(p3 -> System.out.println(p3.getDataCadastro()));
        BigDecimal precodoProduto = produtoDao.buscarPrecoDoProdutoPorNome("SAMSUNG GALAXY");
        System.out.println(precodoProduto);



    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("SAMSUNG GALAXY","Celular intermediario",new BigDecimal("1500"), celulares);

        EntityManager em = JpaUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
