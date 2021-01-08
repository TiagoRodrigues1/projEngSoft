package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Cliente;

@Data
public class ClienteCreateDTO implements CreateDTO<Cliente> {
    private String nome;
    private String username;
    private String password;
    @Override
    public Cliente converter() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.getNome());
        cliente.setPassword(this.getPassword());
        cliente.setUsername(this.getUsername());
        return cliente;
    }
}
