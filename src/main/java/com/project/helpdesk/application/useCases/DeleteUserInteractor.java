package com.project.helpdesk.application.useCases;

import com.project.helpdesk.application.gateways.UserGateway;

public class DeleteUserInteractor {
    
    private final UserGateway userGateway;

    public DeleteUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void deleteUser(Long id) {
        userGateway.deleteUser(id);
    }

    // Lembrete para um futuro próximo:
    // Interactors são importantes para proteger a regra básica de negócio.
    // Por exemplo, quando você quiser implementar verificação (.isAdmin()), vai precisar colocar aqui.
    // Por isso, o interactor deve existir.
}
