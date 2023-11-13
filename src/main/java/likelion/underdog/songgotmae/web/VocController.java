package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.voc.Voc;
import likelion.underdog.songgotmae.domain.voc.VocRepository;
import likelion.underdog.songgotmae.domain.voc.VocService;
import likelion.underdog.songgotmae.web.dto.VocDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vocs")
public class VocController {
    private final VocService vocService;

    @Autowired
    public VocController(VocService vocService) {
        this.vocService = vocService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createPost(@RequestBody @Valid VocDto.CreateRequestDto requestBody, BindingResult bindingResult) {
        VocDto.SaveResponseDto saveResponseDto = vocService.createPost(requestBody);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saveResponseDto);
    }

    @GetMapping("/all")
    public Page<VocDto.FindResponseDto> getVocsPage(
            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
            @RequestParam(required = false, defaultValue = "createdAt", value = "orderby") String criteria,
            @RequestParam(required = false, defaultValue = "DESC", value = "sort") String sort) {
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by(Sort.Order.desc(criteria)));
        return vocService.findAllVocsOrderByCreatedAt(pageable);
      
    @DeleteMapping("/vocs/{postId}")
    public ResponseEntity<VocDto.DeleteResponseDto> deletePost(@PathVariable Long postId) {
        VocDto.DeleteResponseDto deleteResponseDto = vocService.deletePost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deleteResponseDto);
    }
}

