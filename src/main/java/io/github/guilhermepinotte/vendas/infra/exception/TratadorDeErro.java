package io.github.guilhermepinotte.vendas.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErro {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

//    @ExceptionHandler(ValidacaoException.class)
//    public ResponseEntity tratarErroRegraDeNegocio(MethodArgumentNotValidException ex){
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError e) {
            this(e.getField(),e.getDefaultMessage());
        }
    }

}
