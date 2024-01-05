package br.com.unidac.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unidac.model.Collaborator;
import br.com.unidac.model.CollaboratorDTO;
import br.com.unidac.repository.CollaboratorRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CollaboratorController {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @GetMapping("/api/collaborators")
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    @PostMapping("/api/collaborators")
    public ResponseEntity<Collaborator> createCollaborator(@RequestBody CollaboratorDTO collaborator) {
        Collaborator saveCollaborator = new Collaborator(collaborator.getName(), collaborator.getCpf());        
        return ResponseEntity.ok(collaboratorRepository.save(saveCollaborator));
    }

    @GetMapping("/api/collaborators/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable Long id) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
        return ResponseEntity.ok(collaborator);
    }

    @PutMapping("/api/collaborators/{id}")
    public ResponseEntity<Collaborator> updateCollaborator(@PathVariable Long id, @RequestBody Collaborator collaboratorDetails) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        collaborator.setCpf(collaboratorDetails.getCpf());
        collaborator.setName(collaboratorDetails.getName());

        Collaborator updatedCollaborator = collaboratorRepository.save(collaborator);
        return ResponseEntity.ok(updatedCollaborator);
    }

    @DeleteMapping("/api/collaborators/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCollaborator(@PathVariable Long id) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        collaboratorRepository.delete(collaborator);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
