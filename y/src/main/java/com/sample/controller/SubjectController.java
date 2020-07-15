package com.sample.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.sample.model.Subject;
import com.sample.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(@RequestParam(required = false) String title) {
        try {
            List<Subject> subject = new ArrayList<Subject>();

            if (title == null)
                subjectRepository.findAll().forEach(subject::add);
            else
                subjectRepository.findByTitleContaining(title).forEach(subject::add);

            if (subject.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(subject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") long id) {
        Optional<Subject> subjectData = subjectRepository.findById(id);

        if (subjectData.isPresent()) {
            return new ResponseEntity<>(subjectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        try {
            Subject _subject = subjectRepository
                    .save(new Subject(subject.getTitle(), subject.getDescription(), false));
            return new ResponseEntity<>(_subject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject) {
        Optional<Subject> subjectData = subjectRepository.findById(id);

        if (subjectData.isPresent()) {
            Subject _subject = subjectData.get();
            _subject.setTitle(subject.getTitle());
            _subject.setDescription(subject.getDescription());
            _subject.setPublished(subject.isPublished());
            return new ResponseEntity<>(subjectRepository.save(_subject), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("id") long id) {
        try {
            subjectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/subjects")
    public ResponseEntity<HttpStatus> deleteAllSubjects() {
        try {
            subjectRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/subjects/published")
    public ResponseEntity<List<Subject>> findByPublished() {
        try {
            List<Subject> subjects = subjectRepository.findByPublished(true);

            if (subjects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
