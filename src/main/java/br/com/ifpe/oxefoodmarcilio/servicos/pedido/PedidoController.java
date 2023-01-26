package br.com.ifpe.oxefoodmarcilio.servicos.pedido;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodmarcilio.modelo.empresa.Empresa;
import br.com.ifpe.oxefoodmarcilio.modelo.empresa.EmpresaService;
import br.com.ifpe.oxefoodmarcilio.modelo.pedido.Pedido;
import br.com.ifpe.oxefoodmarcilio.modelo.pedido.PedidoService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/pedido")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private EmpresaService empresaService;

  @ApiOperation(value = "Serviço responsável por salvar um pedido no sistema.")
  @PostMapping
  public ResponseEntity<Pedido> save(@RequestBody @Valid PedidoRequest request) {
    Pedido pedido = request.buildPedido();
    Empresa empresa = empresaService.findById(request.getEmpresaId());
    pedido.setEmpresa(empresa);
    Pedido pedidoSalvo = pedidoService.save(pedido);
    return new ResponseEntity<Pedido>(pedidoSalvo, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Serviço responsável por atualizar um pedido no sistema.")
  @PutMapping("/{id}")
  public ResponseEntity<Pedido> update(@PathVariable("id") Long id, @RequestBody String status) {
    Pedido pedido = pedidoService.findById(id);
    pedido.setStatus(status);
    Pedido pedidoAtualizado = pedidoService.update(id, pedido);
    return new ResponseEntity<Pedido>(pedidoAtualizado, HttpStatus.OK);
  }

  @ApiOperation(value = "Serviço responsável por obter um pedido no sistema.")
  @GetMapping("/{id}")
  public ResponseEntity<Pedido> findById(@PathVariable("id") Long id) {
    Pedido pedido = pedidoService.findById(id);
    return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
  }

  @ApiOperation(value = "Serviço responsável por obter todos os pedidos no sistema.")
  @GetMapping("/porclienteid/{id}")
  public ResponseEntity<List<Pedido>> findAllByClienteId(@PathVariable("id") Long id) {
    List<Pedido> pedidos = pedidoService.findAllByClienteId(id);
    return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
  }
}
