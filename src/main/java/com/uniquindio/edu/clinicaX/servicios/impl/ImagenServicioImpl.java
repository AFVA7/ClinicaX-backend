package com.uniquindio.edu.clinicaX.servicios.impl;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.uniquindio.edu.clinicaX.servicios.interfaces.ImagenServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImagenServicioImpl implements ImagenServicios {
    private final Cloudinary cloudinary;

    public ImagenServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dmp5l88nh");
        config.put("api_key", "657353111774894");
        config.put("api_secret", "iPDC5j1-WInT9BnGAkg8Phl-Nj8");
        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);

        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                "uniquindio/clinicaX/fotos"));//? es esta rute?
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
