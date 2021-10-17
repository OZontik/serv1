package zozo.company.servlet;

import zozo.company.controller.PostController;
import zozo.company.repository.PostRepository;
import zozo.company.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
  public static final String ENDPOINT = "/api/posts";
  public static final String BORDER = "/";
  private PostController controller;

  @Override
  public void init() {
    final var repository = new PostRepository();
    final var service = new PostService(repository);
    controller = new PostController(service);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final var path = req.getRequestURI();
    System.out.println(path);
    if (path.equals(ENDPOINT)) {
      controller.all(resp);
      return;
    }
    if (path.matches(ENDPOINT + "\\d+")) {

      final var id = Long.parseLong(path.substring(path.lastIndexOf(BORDER)));
      controller.getById(id, resp);
      return;
    }
    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final var path = req.getRequestURI();
    if (path.equals(ENDPOINT)) {
      controller.save(req.getReader(), resp);
      return;
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final var path = req.getRequestURI();
    if (path.matches(ENDPOINT + "\\d+")) {

      final var id = Long.parseLong(path.substring(path.lastIndexOf(BORDER)));
      controller.removeById(id, resp);
      return;
    }
  }
}


