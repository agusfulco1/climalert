package ar.edu.utn.ba.ddsi.climalert.services.Impl;

import ar.edu.utn.ba.ddsi.climalert.config.RestEmailProperties;
import ar.edu.utn.ba.ddsi.climalert.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.entities.TipoAlerta;
import ar.edu.utn.ba.ddsi.climalert.exceptions.MailException;
import ar.edu.utn.ba.ddsi.climalert.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmailServiceImpl implements EmailService {
    private final RestEmailProperties propiedades;
    private final JavaMailSender mailSender;

    public EmailServiceImpl(RestEmailProperties propiedades, JavaMailSender mailSender) {
        this.propiedades = propiedades;
        this.mailSender = mailSender;
    }

    @Override
    public void enviarAlertas(Clima clima, TipoAlerta tipoAlerta) {
        try {
            List<String> destinatarios = propiedades.getDestinatarios();

            SimpleMailMessage mensaje = new SimpleMailMessage();

            mensaje.setFrom(propiedades.getUsername());

            String[] arrayDestinatarios = destinatarios.toArray(new String[0]);
            mensaje.setTo(arrayDestinatarios);

            mensaje.setSubject("ALERTA CLIMATICA " + tipoAlerta + " en " + clima.getCiudad());

            String cuerpo = "Se ha detectado una condicion climatica .\n\n" +
                    "Detalles:\n" +
                    "- Ciudad: " + clima.getCiudad() + "\n" +
                    "- Temperatura: " + clima.getTemperatura() + "°C\n" +
                    "- Humedad: " + clima.getHumedad() + "%\n";
            mensaje.setText(cuerpo);

            mailSender.send(mensaje);
        } catch (Exception e) {
            throw new MailException("Error al enviar el correo de alerta: " + e);
        }

    }
}
