package io.github.lucianodacunha.app.controller;

import javax.validation.Valid;

import io.github.lucianodacunha.app.dto.RequisicaoNovoPedido;
import io.github.lucianodacunha.app.model.Pedido;
import io.github.lucianodacunha.app.model.User;
import io.github.lucianodacunha.app.repository.PedidoRepository;
import io.github.lucianodacunha.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicao) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
        if(result.hasErrors()) {
            return "pedido/formulario";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User usuario = userRepository.findByUsername(username);
        Pedido pedido = requisicao.toPedido();
        pedido.setUser(usuario);
        pedidoRepository.save(pedido);
        return "redirect:/home";
    }
}