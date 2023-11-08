package io.github.lucianodacunha.app.api;

import java.util.List;
import io.github.lucianodacunha.app.interceptor.InterceptadorDeAcessos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("acessos")
@RestController
public class AcessosRest {

	@GetMapping
	public List<InterceptadorDeAcessos.Acesso> getAcessos() {
		return InterceptadorDeAcessos.acessos;
	}
}