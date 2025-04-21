    package com.example.demo.controller;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import com.example.demo.dto.request.BukuRequest;
    import com.example.demo.entity.Buku;
    import com.example.demo.service.BukuService;
    import com.example.demo.service.MinioService;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.http.MediaType;

    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("/buku")
    public class BukuController {

        private final BukuService bukuService;
        private final MinioService minioService;

        public BukuController(BukuService bukuService, MinioService minioService) {
            this.bukuService = bukuService;
            this.minioService = minioService;
        }

        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Buku> tambahBuku(
                @RequestPart("bukuRequest") String bukuRequestJson,
                @RequestPart(value = "coverImage", required = false) MultipartFile coverImage)
                throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            BukuRequest bukuRequest = objectMapper.readValue(bukuRequestJson, BukuRequest.class);

            return ResponseEntity.ok(bukuService.tambahBuku(bukuRequest, coverImage));
        }

        @GetMapping
        public ResponseEntity<List<Buku>> getAllBuku() {
            return ResponseEntity.ok(bukuService.getAllBuku());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Buku> getBukuById(@PathVariable Long id) {
            return ResponseEntity.of(bukuService.getBukuById(id));
        }

        @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Buku> updateBuku(
                @PathVariable Long id,
                @RequestPart("bukuRequest") String bukuRequestJson,
                @RequestPart(value = "coverImage", required = false) MultipartFile coverImage)
                throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            BukuRequest bukuRequest = objectMapper.readValue(bukuRequestJson, BukuRequest.class);

            return ResponseEntity.ok(bukuService.updateBuku(id, bukuRequest, coverImage));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBuku(@PathVariable Long id) {
            bukuService.deleteBuku(id);
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/upload")
        public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
            String fileUrl = minioService.uploadFile(file);
            return ResponseEntity.ok().body(Map.of("message", "File uploaded successfully", "fileUrl", fileUrl));
        }
    }