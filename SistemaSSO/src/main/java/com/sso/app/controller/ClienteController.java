package com.sso.app.controller;

import com.sso.app.entity.Cliente;
import com.sso.app.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
@AllArgsConstructor
@CrossOrigin
public class    ClienteController extends BaseController<Cliente>{

    private final ClienteService clienteService;

    @Override
    protected  List<Cliente> findAllActive() {return clienteService.findAllActive();}

    @Override
    protected Optional<Cliente> findById(Long id){return clienteService.findById(id);}

    @Override
    protected Cliente save(Cliente entity) {return clienteService.save(entity);}// entity es el objeto que recibirías cuando llamás al método desde otra parte del código.

    @Override
    protected void deleteById(Long id) {clienteService.deleteById(id);}

    @Override
    protected void setId(Cliente entity, Long id) {entity.setId(id); }


    @GetMapping("/buscar")
    public ResponseEntity<Cliente> getClienteByRazonSocial(@RequestParam String razonSocial) {
        Optional<Cliente> cliente = clienteService.findByRazonSocial(razonSocial);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
