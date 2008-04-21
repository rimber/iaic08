
; SISTEMA DE IFERENCIA DE MODELO DE TELÉFONO MÓVIL

; Definimos el patrón para los modelos

(deftemplate modeloMovil 
	(slot nombre (type STRING)) 
	(slot compania (type STRING))
	(slot precio (type FLOAT)) 
	(slot peso (type FLOAT))
	(slot alto (type FLOAT))
	(slot ancho (type FLOAT))
	(slot profundo (type FLOAT))
	(slot manosLibres (type ATOM))
	(slot camara (type FLOAT))
	(slot memoria (type FLOAT))
	(slot mp3 (type ATOM))
	(slot bandas(type FLOAT))
	(slot durBateriaEspera (type FLOAT))
	(slot durBateriaConv (type FLOAT))
	(slot calidad (type STRING))
		
)

; Insertamos los hechos para cada modelo

; LG KG800
(assert (modeloMovil 
	(nombre “KG800”) 
	(compania “Movistar”)
	(precio 149) 
	(peso 83) 
	(alto 92) 
	(ancho 49) 
	(profundo 15) 
	(manosLibres FALSE)
	(camara 1.3)
	(memoria 128)
	(mp3 TRUE)
	(badnas 3)
	(durBateriaEspera 200)
	(durBateriaConv 3)
	(calidad “Bueno”)	
	)
)

; Definimos las reglas
(defrule nombreRegla (condición) => (acción))

