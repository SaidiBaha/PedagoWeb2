package tn.esprit.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Option;
import tn.esprit.spring.services.OptionServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/options")
public class OptionController {


    private OptionServiceImp optionService;

    @GetMapping
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/{id}")
    public Option getOptionById(@PathVariable Long id) {
        return optionService.getOptionById(id);
    }

    @PostMapping("/{questionId}")
    public Option createOption(@PathVariable Long questionId, @RequestBody Option option) {
        return optionService.createOption(questionId, option);
    }

    @PutMapping("/{id}")
    public Option updateOption(@PathVariable Long id, @RequestBody Option option) {
        return optionService.updateOption(id, option);
    }

    @DeleteMapping("/{id}")
    public void deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
    }



    @GetMapping("/option/{questionId}")
    public ResponseEntity<?> getOptionsByQuestionId(@PathVariable Long questionId){
        return ResponseEntity.ok(optionService.getOptionByQuestionId(questionId));
    }


}
