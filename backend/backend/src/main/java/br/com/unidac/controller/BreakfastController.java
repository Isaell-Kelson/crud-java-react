package br.com.unidac.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.unidac.model.Breakfast;
import br.com.unidac.model.Collaborator;
import br.com.unidac.repository.BreakfastRepository;
import br.com.unidac.repository.CollaboratorRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BreakfastController {

    @Autowired
    private BreakfastRepository breakfastRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @GetMapping("/api/breakfasts")
    public List<Breakfast> getAllBreakfasts() {
        return breakfastRepository.findAll();
    }

    @PostMapping("/api/breakfasts")
    public Breakfast createBreakfast(@RequestBody Breakfast breakfast) {
        Date currentDate = new Date();

        if (breakfast.getDate() != null && breakfast.getDate().before(currentDate)) {
            throw new IllegalArgumentException("A data do café da manhã deve ser posterior à data atual.");
        }

        List<Breakfast> existingOptions = breakfastRepository
                .findByBreakfastOptionAndDate(breakfast.getBreakfastOption(), breakfast.getDate());
        if (!existingOptions.isEmpty()) {
            throw new IllegalArgumentException(
                    "Essa opção de café da manhã já foi escolhida por outro colaborador para a mesma data.");
        }

        Collaborator collaborator = collaboratorRepository.findById(breakfast.getCollaborator().getId())
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        breakfast.setCollaborator(collaborator);
        return breakfastRepository.save(breakfast);
    }

    @GetMapping("/api/breakfasts/{id}")
    public Breakfast getBreakfastById(@PathVariable Long id) {
        return breakfastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Café da manhã não encontrado"));
    }

    @PutMapping("/api/breakfasts/{id}")
    public Breakfast updateBreakfast(@PathVariable Long id, @RequestBody Breakfast breakfastDetails) {
        Breakfast breakfast = breakfastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Café da manhã não encontrado"));

        breakfast.setBreakfastOption(breakfastDetails.getBreakfastOption());
        breakfast.setDate(breakfastDetails.getDate());

        return breakfastRepository.save(breakfast);
    }

    @DeleteMapping("/api/breakfasts/{id}")
    public void deleteBreakfast(@PathVariable Long id) {
        Breakfast breakfast = breakfastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Café da manhã não encontrado"));

        breakfastRepository.delete(breakfast);
    }
}
