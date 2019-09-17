/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "citapersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citapersona.findAll", query = "SELECT c FROM Citapersona c")
    , @NamedQuery(name = "Citapersona.findByCodigoCitaPersona", query = "SELECT c FROM Citapersona c WHERE c.codigoCitaPersona = :codigoCitaPersona")
    , @NamedQuery(name = "Citapersona.findByPrimerApellido", query = "SELECT c FROM Citapersona c WHERE c.primerApellido = :primerApellido")
    , @NamedQuery(name = "Citapersona.findBySegundoApellido", query = "SELECT c FROM Citapersona c WHERE c.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Citapersona.findByPrimerNombre", query = "SELECT c FROM Citapersona c WHERE c.primerNombre = :primerNombre")
    , @NamedQuery(name = "Citapersona.findBySegundoNombre", query = "SELECT c FROM Citapersona c WHERE c.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Citapersona.findByDireccion", query = "SELECT c FROM Citapersona c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Citapersona.findByTelefono", query = "SELECT c FROM Citapersona c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Citapersona.findByEmail", query = "SELECT c FROM Citapersona c WHERE c.email = :email")
    , @NamedQuery(name = "Citapersona.findByEdad", query = "SELECT c FROM Citapersona c WHERE c.edad = :edad")
    , @NamedQuery(name = "Citapersona.findByEdadMeses", query = "SELECT c FROM Citapersona c WHERE c.edadMeses = :edadMeses")
    , @NamedQuery(name = "Citapersona.findByEdadAnos", query = "SELECT c FROM Citapersona c WHERE c.edadAnos = :edadAnos")
    , @NamedQuery(name = "Citapersona.findByEdadPersona", query = "SELECT c FROM Citapersona c WHERE c.edadPersona = :edadPersona")
    , @NamedQuery(name = "Citapersona.findByPersonasACargo", query = "SELECT c FROM Citapersona c WHERE c.personasACargo = :personasACargo")
    , @NamedQuery(name = "Citapersona.findByNivelAfiliacion", query = "SELECT c FROM Citapersona c WHERE c.nivelAfiliacion = :nivelAfiliacion")
    , @NamedQuery(name = "Citapersona.findByTecnico", query = "SELECT c FROM Citapersona c WHERE c.tecnico = :tecnico")
    , @NamedQuery(name = "Citapersona.findByTecnologo", query = "SELECT c FROM Citapersona c WHERE c.tecnologo = :tecnologo")
    , @NamedQuery(name = "Citapersona.findByProfesional", query = "SELECT c FROM Citapersona c WHERE c.profesional = :profesional")
    , @NamedQuery(name = "Citapersona.findByEstadoActual", query = "SELECT c FROM Citapersona c WHERE c.estadoActual = :estadoActual")
    , @NamedQuery(name = "Citapersona.findByBttonActivo", query = "SELECT c FROM Citapersona c WHERE c.bttonActivo = :bttonActivo")
    , @NamedQuery(name = "Citapersona.findByCscIngreso", query = "SELECT c FROM Citapersona c WHERE c.cscIngreso = :cscIngreso")
    , @NamedQuery(name = "Citapersona.findByFechaSolicitud", query = "SELECT c FROM Citapersona c WHERE c.fechaSolicitud = :fechaSolicitud")
    , @NamedQuery(name = "Citapersona.findByFechaCita", query = "SELECT c FROM Citapersona c WHERE c.fechaCita = :fechaCita")
    , @NamedQuery(name = "Citapersona.findByFechaModificacion", query = "SELECT c FROM Citapersona c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Citapersona.findByFechaNacimiento", query = "SELECT c FROM Citapersona c WHERE c.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Citapersona.findByFechaExpedicionDocumento", query = "SELECT c FROM Citapersona c WHERE c.fechaExpedicionDocumento = :fechaExpedicionDocumento")
    , @NamedQuery(name = "Citapersona.findByFinalizada", query = "SELECT c FROM Citapersona c WHERE c.finalizada = :finalizada")
    , @NamedQuery(name = "Citapersona.findByBotDiag", query = "SELECT c FROM Citapersona c WHERE c.botDiag = :botDiag")
    , @NamedQuery(name = "Citapersona.findByBotAnteFami", query = "SELECT c FROM Citapersona c WHERE c.botAnteFami = :botAnteFami")
    , @NamedQuery(name = "Citapersona.findByBotCitaFinalizada", query = "SELECT c FROM Citapersona c WHERE c.botCitaFinalizada = :botCitaFinalizada")
    , @NamedQuery(name = "Citapersona.findByBotAntcGenerales", query = "SELECT c FROM Citapersona c WHERE c.botAntcGenerales = :botAntcGenerales")
    , @NamedQuery(name = "Citapersona.findByBotDatCargo", query = "SELECT c FROM Citapersona c WHERE c.botDatCargo = :botDatCargo")
    , @NamedQuery(name = "Citapersona.findByBotAntGineco", query = "SELECT c FROM Citapersona c WHERE c.botAntGineco = :botAntGineco")
    , @NamedQuery(name = "Citapersona.findByBotGenero", query = "SELECT c FROM Citapersona c WHERE c.botGenero = :botGenero")
    , @NamedQuery(name = "Citapersona.findByBotnGenero", query = "SELECT c FROM Citapersona c WHERE c.botnGenero = :botnGenero")
    , @NamedQuery(name = "Citapersona.findByBotToxAlerg", query = "SELECT c FROM Citapersona c WHERE c.botToxAlerg = :botToxAlerg")
    , @NamedQuery(name = "Citapersona.findByBotExFisico", query = "SELECT c FROM Citapersona c WHERE c.botExFisico = :botExFisico")
    , @NamedQuery(name = "Citapersona.findByBotExFisico1", query = "SELECT c FROM Citapersona c WHERE c.botExFisico1 = :botExFisico1")
    , @NamedQuery(name = "Citapersona.findByBotExFisico2", query = "SELECT c FROM Citapersona c WHERE c.botExFisico2 = :botExFisico2")
    , @NamedQuery(name = "Citapersona.findByBotExFisico3", query = "SELECT c FROM Citapersona c WHERE c.botExFisico3 = :botExFisico3")
    , @NamedQuery(name = "Citapersona.findByBotExFisico4", query = "SELECT c FROM Citapersona c WHERE c.botExFisico4 = :botExFisico4")
    , @NamedQuery(name = "Citapersona.findByBotExFisico5", query = "SELECT c FROM Citapersona c WHERE c.botExFisico5 = :botExFisico5")
    , @NamedQuery(name = "Citapersona.findByBotInmunizacion", query = "SELECT c FROM Citapersona c WHERE c.botInmunizacion = :botInmunizacion")
    , @NamedQuery(name = "Citapersona.findByBotAntecOcupacionales", query = "SELECT c FROM Citapersona c WHERE c.botAntecOcupacionales = :botAntecOcupacionales")
    , @NamedQuery(name = "Citapersona.findByBotTrabajoActual", query = "SELECT c FROM Citapersona c WHERE c.botTrabajoActual = :botTrabajoActual")
    , @NamedQuery(name = "Citapersona.findByBotExamenIngreso", query = "SELECT c FROM Citapersona c WHERE c.botExamenIngreso = :botExamenIngreso")
    , @NamedQuery(name = "Citapersona.findByBotElementosProteccion", query = "SELECT c FROM Citapersona c WHERE c.botElementosProteccion = :botElementosProteccion")
    , @NamedQuery(name = "Citapersona.findByBotIncapacidades", query = "SELECT c FROM Citapersona c WHERE c.botIncapacidades = :botIncapacidades")
    , @NamedQuery(name = "Citapersona.findByBotConsultaMedica", query = "SELECT c FROM Citapersona c WHERE c.botConsultaMedica = :botConsultaMedica")
    , @NamedQuery(name = "Citapersona.findByBotProcedimiento", query = "SELECT c FROM Citapersona c WHERE c.botProcedimiento = :botProcedimiento")
    , @NamedQuery(name = "Citapersona.findByBotRecomMedicas", query = "SELECT c FROM Citapersona c WHERE c.botRecomMedicas = :botRecomMedicas")
    , @NamedQuery(name = "Citapersona.findByBotExamApoyDiagnostico", query = "SELECT c FROM Citapersona c WHERE c.botExamApoyDiagnostico = :botExamApoyDiagnostico")
    , @NamedQuery(name = "Citapersona.findByBotRecomMedicaOtros", query = "SELECT c FROM Citapersona c WHERE c.botRecomMedicaOtros = :botRecomMedicaOtros")
    , @NamedQuery(name = "Citapersona.findByBotFinalizar", query = "SELECT c FROM Citapersona c WHERE c.botFinalizar = :botFinalizar")
    , @NamedQuery(name = "Citapersona.findByBotRevisionSistem", query = "SELECT c FROM Citapersona c WHERE c.botRevisionSistem = :botRevisionSistem")
    , @NamedQuery(name = "Citapersona.findByBotTalla", query = "SELECT c FROM Citapersona c WHERE c.botTalla = :botTalla")
    , @NamedQuery(name = "Citapersona.findByBotVista", query = "SELECT c FROM Citapersona c WHERE c.botVista = :botVista")
    , @NamedQuery(name = "Citapersona.findByBotOrl", query = "SELECT c FROM Citapersona c WHERE c.botOrl = :botOrl")
    , @NamedQuery(name = "Citapersona.findByBotCuello", query = "SELECT c FROM Citapersona c WHERE c.botCuello = :botCuello")
    , @NamedQuery(name = "Citapersona.findByBotTorax", query = "SELECT c FROM Citapersona c WHERE c.botTorax = :botTorax")
    , @NamedQuery(name = "Citapersona.findByBotAparRespiratorio", query = "SELECT c FROM Citapersona c WHERE c.botAparRespiratorio = :botAparRespiratorio")
    , @NamedQuery(name = "Citapersona.findByBotApatCardioVascular", query = "SELECT c FROM Citapersona c WHERE c.botApatCardioVascular = :botApatCardioVascular")
    , @NamedQuery(name = "Citapersona.findByBotAparDigestivo", query = "SELECT c FROM Citapersona c WHERE c.botAparDigestivo = :botAparDigestivo")
    , @NamedQuery(name = "Citapersona.findByBotGenitoUrinario", query = "SELECT c FROM Citapersona c WHERE c.botGenitoUrinario = :botGenitoUrinario")
    , @NamedQuery(name = "Citapersona.findByBotMusculoEsqueletico", query = "SELECT c FROM Citapersona c WHERE c.botMusculoEsqueletico = :botMusculoEsqueletico")
    , @NamedQuery(name = "Citapersona.findByBotSistemaNervioso", query = "SELECT c FROM Citapersona c WHERE c.botSistemaNervioso = :botSistemaNervioso")
    , @NamedQuery(name = "Citapersona.findByBotPieAnexos", query = "SELECT c FROM Citapersona c WHERE c.botPieAnexos = :botPieAnexos")
    , @NamedQuery(name = "Citapersona.findByBotSistLinfatico", query = "SELECT c FROM Citapersona c WHERE c.botSistLinfatico = :botSistLinfatico")
    , @NamedQuery(name = "Citapersona.findByBotSisEndocrino", query = "SELECT c FROM Citapersona c WHERE c.botSisEndocrino = :botSisEndocrino")
    , @NamedQuery(name = "Citapersona.findByBotEstadoMental", query = "SELECT c FROM Citapersona c WHERE c.botEstadoMental = :botEstadoMental")
    , @NamedQuery(name = "Citapersona.findByBotObservacionesRecomendaciones", query = "SELECT c FROM Citapersona c WHERE c.botObservacionesRecomendaciones = :botObservacionesRecomendaciones")
    , @NamedQuery(name = "Citapersona.findByBotConcOcupacional", query = "SELECT c FROM Citapersona c WHERE c.botConcOcupacional = :botConcOcupacional")
    , @NamedQuery(name = "Citapersona.findByFacturada", query = "SELECT c FROM Citapersona c WHERE c.facturada = :facturada")
    , @NamedQuery(name = "Citapersona.findByIngresoPerson", query = "SELECT c FROM Citapersona c WHERE c.ingresoPerson = :ingresoPerson")
    , @NamedQuery(name = "Citapersona.findByNumeroDocumentoIdentidad", query = "SELECT c FROM Citapersona c WHERE c.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")
    , @NamedQuery(name = "Citapersona.findByEstadoFactura", query = "SELECT c FROM Citapersona c WHERE c.estadoFactura = :estadoFactura")
    , @NamedQuery(name = "Citapersona.findByCodigoEpsSede", query = "SELECT c FROM Citapersona c WHERE c.codigoEpsSede = :codigoEpsSede")
    , @NamedQuery(name = "Citapersona.findByCodigoDepartamentoRips", query = "SELECT c FROM Citapersona c WHERE c.codigoDepartamentoRips = :codigoDepartamentoRips")
    , @NamedQuery(name = "Citapersona.findByCodigoMunicipioRip", query = "SELECT c FROM Citapersona c WHERE c.codigoMunicipioRip = :codigoMunicipioRip")
    , @NamedQuery(name = "Citapersona.findByClasificacionIdentificacion", query = "SELECT c FROM Citapersona c WHERE c.clasificacionIdentificacion = :clasificacionIdentificacion")
    , @NamedQuery(name = "Citapersona.findByCodigoEntidadPromotora", query = "SELECT c FROM Citapersona c WHERE c.codigoEntidadPromotora = :codigoEntidadPromotora")
    , @NamedQuery(name = "Citapersona.findByCitaActiva", query = "SELECT c FROM Citapersona c WHERE c.citaActiva = :citaActiva")})
public class Citapersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCitaPersona")
    private Long codigoCitaPersona;
    @Size(max = 100)
    @Column(name = "primerApellido")
    private String primerApellido;
    @Size(max = 100)
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @Size(max = 100)
    @Column(name = "primerNombre")
    private String primerNombre;
    @Size(max = 100)
    @Column(name = "segundoNombre")
    private String segundoNombre;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 350)
    @Column(name = "email")
    private String email;
    @Column(name = "edad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "edadMeses")
    private BigDecimal edadMeses;
    @Column(name = "edadAnos")
    private BigDecimal edadAnos;
    @Size(max = 4)
    @Column(name = "edadPersona")
    private String edadPersona;
    @Size(max = 3)
    @Column(name = "personasACargo")
    private String personasACargo;
    @Size(max = 1)
    @Column(name = "nivelAfiliacion")
    private String nivelAfiliacion;
    @Lob
    @Column(name = "fotoPersona")
    private Serializable fotoPersona;
    @Size(max = 1)
    @Column(name = "tecnico")
    private String tecnico;
    @Size(max = 1)
    @Column(name = "tecnologo")
    private String tecnologo;
    @Size(max = 1)
    @Column(name = "profesional")
    private String profesional;
    @Lob
    @Column(name = "otrosEstudios")
    private String otrosEstudios;
    @Size(max = 1)
    @Column(name = "estadoActual")
    private String estadoActual;
    @Size(max = 1)
    @Column(name = "bttonActivo")
    private String bttonActivo;
    @Column(name = "cscIngreso")
    private Long cscIngreso;
    @Column(name = "fechaSolicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "fechaCita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCita;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "fechaExpedicionDocumento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpedicionDocumento;
    @Size(max = 1)
    @Column(name = "finalizada")
    private String finalizada;
    @Size(max = 1)
    @Column(name = "botDiag")
    private String botDiag;
    @Size(max = 1)
    @Column(name = "botAnteFami")
    private String botAnteFami;
    @Size(max = 1)
    @Column(name = "botCitaFinalizada")
    private String botCitaFinalizada;
    @Size(max = 1)
    @Column(name = "botAntcGenerales")
    private String botAntcGenerales;
    @Size(max = 1)
    @Column(name = "botDatCargo")
    private String botDatCargo;
    @Size(max = 1)
    @Column(name = "botAntGineco")
    private String botAntGineco;
    @Size(max = 1)
    @Column(name = "botGenero")
    private String botGenero;
    @Size(max = 1)
    @Column(name = "botnGenero")
    private String botnGenero;
    @Size(max = 1)
    @Column(name = "botToxAlerg")
    private String botToxAlerg;
    @Size(max = 1)
    @Column(name = "botExFisico")
    private String botExFisico;
    @Size(max = 1)
    @Column(name = "botExFisico1")
    private String botExFisico1;
    @Size(max = 1)
    @Column(name = "botExFisico2")
    private String botExFisico2;
    @Size(max = 1)
    @Column(name = "botExFisico3")
    private String botExFisico3;
    @Size(max = 1)
    @Column(name = "botExFisico4")
    private String botExFisico4;
    @Size(max = 1)
    @Column(name = "botExFisico5")
    private String botExFisico5;
    @Size(max = 1)
    @Column(name = "botInmunizacion")
    private String botInmunizacion;
    @Size(max = 1)
    @Column(name = "botAntecOcupacionales")
    private String botAntecOcupacionales;
    @Size(max = 1)
    @Column(name = "botTrabajoActual")
    private String botTrabajoActual;
    @Size(max = 1)
    @Column(name = "botExamenIngreso")
    private String botExamenIngreso;
    @Size(max = 1)
    @Column(name = "botElementosProteccion")
    private String botElementosProteccion;
    @Size(max = 1)
    @Column(name = "botIncapacidades")
    private String botIncapacidades;
    @Size(max = 1)
    @Column(name = "botConsultaMedica")
    private String botConsultaMedica;
    @Size(max = 1)
    @Column(name = "botProcedimiento")
    private String botProcedimiento;
    @Size(max = 1)
    @Column(name = "botRecomMedicas")
    private String botRecomMedicas;
    @Size(max = 1)
    @Column(name = "botExamApoyDiagnostico")
    private String botExamApoyDiagnostico;
    @Size(max = 1)
    @Column(name = "botRecomMedicaOtros")
    private String botRecomMedicaOtros;
    @Size(max = 1)
    @Column(name = "botFinalizar")
    private String botFinalizar;
    @Size(max = 1)
    @Column(name = "botRevisionSistem")
    private String botRevisionSistem;
    @Size(max = 1)
    @Column(name = "botTalla")
    private String botTalla;
    @Size(max = 1)
    @Column(name = "botVista")
    private String botVista;
    @Size(max = 1)
    @Column(name = "botOrl")
    private String botOrl;
    @Size(max = 1)
    @Column(name = "botCuello")
    private String botCuello;
    @Size(max = 1)
    @Column(name = "botTorax")
    private String botTorax;
    @Size(max = 1)
    @Column(name = "botAparRespiratorio")
    private String botAparRespiratorio;
    @Size(max = 1)
    @Column(name = "botApatCardioVascular")
    private String botApatCardioVascular;
    @Size(max = 1)
    @Column(name = "botAparDigestivo")
    private String botAparDigestivo;
    @Size(max = 1)
    @Column(name = "botGenitoUrinario")
    private String botGenitoUrinario;
    @Size(max = 1)
    @Column(name = "botMusculoEsqueletico")
    private String botMusculoEsqueletico;
    @Size(max = 1)
    @Column(name = "botSistemaNervioso")
    private String botSistemaNervioso;
    @Size(max = 1)
    @Column(name = "botPieAnexos")
    private String botPieAnexos;
    @Size(max = 1)
    @Column(name = "botSistLinfatico")
    private String botSistLinfatico;
    @Size(max = 1)
    @Column(name = "botSisEndocrino")
    private String botSisEndocrino;
    @Size(max = 1)
    @Column(name = "botEstadoMental")
    private String botEstadoMental;
    @Size(max = 1)
    @Column(name = "botObservacionesRecomendaciones")
    private String botObservacionesRecomendaciones;
    @Size(max = 1)
    @Column(name = "botConcOcupacional")
    private String botConcOcupacional;
    @Size(max = 1)
    @Column(name = "facturada")
    private String facturada;
    @Column(name = "ingresoPerson")
    private Integer ingresoPerson;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @Size(max = 1)
    @Column(name = "estadoFactura")
    private String estadoFactura;
    @Column(name = "codigoEpsSede")
    private Long codigoEpsSede;
    @Size(max = 1)
    @Column(name = "citaActiva")
    private String citaActiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCitaPersona")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoCita")
    private List<Recomendacionesmedicamentos> recomendacionesmedicamentosList;
    @OneToMany(mappedBy = "codigoCitaPersona")
    private List<CarteraSede> carteraSedeList;
    @OneToMany(mappedBy = "codigoCitaPersona")
    private List<FacturaRadicacion> facturaRadicacionList;
    @JoinColumn(name = "codigoCargo", referencedColumnName = "codigoCargoPersona")
    @ManyToOne
    private Cargopersona codigoCargo;
    @JoinColumn(name = "codigoArea", referencedColumnName = "codigoArea")
    @ManyToOne
    private Area codigoArea;
    @JoinColumn(name = "codigoExamenRealizar", referencedColumnName = "codigo")
    @ManyToOne
    private Tipoexamen codigoExamenRealizar;
    @JoinColumn(name = "upz", referencedColumnName = "codigoUpz")
    @ManyToOne
    private Upz upz;
    @JoinColumn(name = "codigoReligion", referencedColumnName = "codigoReligion")
    @ManyToOne
    private Religion codigoReligion;
    @JoinColumn(name = "codigoProfesional", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoProfesional;
    @JoinColumn(name = "radicadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario radicadoPor;
    @JoinColumn(name = "genero", referencedColumnName = "codigoGenero")
    @ManyToOne
    private Tipogenero genero;
    @JoinColumn(name = "codigoArl", referencedColumnName = "codigoArl")
    @ManyToOne
    private Arl codigoArl;
    @JoinColumn(name = "primaria", referencedColumnName = "codigoNivelEducativo")
    @ManyToOne
    private NivelEducativo primaria;
    @JoinColumn(name = "codigoBarrio", referencedColumnName = "codigoBarrio")
    @ManyToOne
    private Barrio codigoBarrio;
    @JoinColumn(name = "clasificacionEps", referencedColumnName = "codigoClasificacionEps")
    @ManyToOne
    private ClasificacionEps clasificacionEps;
    @JoinColumn(name = "codigoClasificacionProfesionPersona", referencedColumnName = "codigoClasificacionProfesion")
    @ManyToOne
    private ClasificacionProfesionPersona codigoClasificacionProfesionPersona;
    @JoinColumn(name = "codigoAfiliacion", referencedColumnName = "codigoAfiliacion")
    @ManyToOne
    private Tipoafiliacion codigoAfiliacion;
    @JoinColumn(name = "codigoestadoFactura", referencedColumnName = "codigoEstadoFactura")
    @ManyToOne
    private Estadofactura codigoestadoFactura;
    @JoinColumn(name = "codigoLocalidad", referencedColumnName = "codigoLocalidad")
    @ManyToOne
    private Localidad codigoLocalidad;
    @JoinColumn(name = "codigoEstadoCivil", referencedColumnName = "codigoEstadoCivil")
    @ManyToOne
    private Estadocivil codigoEstadoCivil;
    @JoinColumn(name = "codigoConsultorio", referencedColumnName = "codigoConsultorio")
    @ManyToOne
    private Consultorio codigoConsultorio;
    @JoinColumn(name = "codigoExamen", referencedColumnName = "codigoValorConsulta")
    @ManyToOne
    private Procedimientosede codigoExamen;
    @JoinColumn(name = "codigoProfesionPersona", referencedColumnName = "codigoProfesionPersona")
    @ManyToOne
    private Profesionpersona codigoProfesionPersona;
    @JoinColumn(name = "codigoPersonaCita", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersonaCita;
    @JoinColumn(name = "codigoMunicipioExpedicion", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipioExpedicion;
    @JoinColumn(name = "codigoDependencia", referencedColumnName = "codigoDependencia")
    @ManyToOne
    private Dependencia codigoDependencia;
    @JoinColumn(name = "codigoMunicipioResidencia", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipioResidencia;
    @JoinColumn(name = "codigoMunicipioNacimiento", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipioNacimiento;
    @JoinColumn(name = "codigoEmpresa", referencedColumnName = "consecutivoEmpresa")
    @ManyToOne
    private Empresa codigoEmpresa;
    @JoinColumn(name = "codigoPensiones", referencedColumnName = "codigoPensiones")
    @ManyToOne
    private Pensiones codigoPensiones;
    @JoinColumn(name = "codigoIdentificacion", referencedColumnName = "codigoIdentificacion")
    @ManyToOne
    private Tipoidentificacion codigoIdentificacion;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne(fetch = FetchType.EAGER)
    private Eps codigoEps;
    @JoinColumn(name = "sucursalEmpresa", referencedColumnName = "consecutivoSucursalEmpresa")
    @ManyToOne
    private Sucursalempresa sucursalEmpresa;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "secundaria", referencedColumnName = "codigoNivelEducativo")
    @ManyToOne
    private NivelEducativo secundaria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCita")
    private List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList;
    @OneToMany(mappedBy = "codigoCita")
    private List<Examenfisico> examenfisicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCita")
    private List<Formulamedica> formulamedicaList;
    @OneToMany(mappedBy = "codigoCita")
    private List<Imprimir> imprimirList;
    @OneToMany(mappedBy = "codigoCitaPersona")
    private List<RadicacionFacturas> radicacionFacturasList;
    @OneToMany(mappedBy = "codigoCita")
    private List<Procedimiento> procedimientoList;
    @OneToMany(mappedBy = "codigoCita")
    private List<VacunacionPersona> vacunacionPersonaList;
    @OneToMany(mappedBy = "codigoCita")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList;
    @OneToMany(mappedBy = "codigoCitaEvolucion")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList1;
    @OneToMany(mappedBy = "codigoCita")
    private List<DiagnosticoIngreso> diagnosticoIngresoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCita")
    private List<Consultamedica> consultamedicaList;
    @Size(max = 15)
    @Column(name = "codigoDepartamentoRips")
    private String codigoDepartamentoRips;
    @Size(max = 15)
    @Column(name = "codigoMunicipioRip")
    private String codigoMunicipioRip;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigoDepartamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "codigoTipoUsuario", referencedColumnName = "codigoTipoUsuario")
    @ManyToOne
    private TipoUsuario codigoTipoUsuario;
    @JoinColumn(name = "codigoTipoEdad", referencedColumnName = "codigoTipoEdad")
    @ManyToOne
    private TipoEdad codigoTipoEdad;
    @JoinColumn(name = "codigoZonaResidencia", referencedColumnName = "codigoZona")
    @ManyToOne
    private ZonaResidencia codigoZonaResidencia;
    @Size(max = 10)
    @Column(name = "clasificacionIdentificacion")
    private String clasificacionIdentificacion;
    @Size(max = 50)
    @Column(name = "codigoEntidadPromotora")
    private String codigoEntidadPromotora;
    @JoinColumn(name = "codigoRipsAH", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAH;
    @JoinColumn(name = "codigoRipsAN", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAN;
    @JoinColumn(name = "codigoRipsAT", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAT;
    @JoinColumn(name = "codigoRipsAC", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAC;
    @JoinColumn(name = "codigoRipsCT", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsCT;
    @JoinColumn(name = "codigoRipsUS", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsUS;
    @JoinColumn(name = "codigoRipsAP", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAP;
    @JoinColumn(name = "codigoRipsAU", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAU;
    @JoinColumn(name = "codigoRipsAF", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAF;
    @JoinColumn(name = "codigoRipsAM", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoRipsAM;

    public Citapersona() {
    }

    public Citapersona(Long codigoCitaPersona) {
        this.codigoCitaPersona = codigoCitaPersona;
    }

    public Long getCodigoCitaPersona() {
        return codigoCitaPersona;
    }

    public void setCodigoCitaPersona(Long codigoCitaPersona) {
        this.codigoCitaPersona = codigoCitaPersona;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEdad() {
        return edad;
    }

    public void setEdad(Date edad) {
        this.edad = edad;
    }

    public BigDecimal getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(BigDecimal edadMeses) {
        this.edadMeses = edadMeses;
    }

    public BigDecimal getEdadAnos() {
        return edadAnos;
    }

    public void setEdadAnos(BigDecimal edadAnos) {
        this.edadAnos = edadAnos;
    }

    public String getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(String edadPersona) {
        this.edadPersona = edadPersona;
    }

    public String getPersonasACargo() {
        return personasACargo;
    }

    public void setPersonasACargo(String personasACargo) {
        this.personasACargo = personasACargo;
    }

    public String getNivelAfiliacion() {
        return nivelAfiliacion;
    }

    public void setNivelAfiliacion(String nivelAfiliacion) {
        this.nivelAfiliacion = nivelAfiliacion;
    }

    public Serializable getFotoPersona() {
        return fotoPersona;
    }

    public void setFotoPersona(Serializable fotoPersona) {
        this.fotoPersona = fotoPersona;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getTecnologo() {
        return tecnologo;
    }

    public void setTecnologo(String tecnologo) {
        this.tecnologo = tecnologo;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public String getOtrosEstudios() {
        return otrosEstudios;
    }

    public void setOtrosEstudios(String otrosEstudios) {
        this.otrosEstudios = otrosEstudios;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getBttonActivo() {
        return bttonActivo;
    }

    public void setBttonActivo(String bttonActivo) {
        this.bttonActivo = bttonActivo;
    }

    public Long getCscIngreso() {
        return cscIngreso;
    }

    public void setCscIngreso(Long cscIngreso) {
        this.cscIngreso = cscIngreso;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public String getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(String finalizada) {
        this.finalizada = finalizada;
    }

    public String getBotDiag() {
        return botDiag;
    }

    public void setBotDiag(String botDiag) {
        this.botDiag = botDiag;
    }

    public String getBotAnteFami() {
        return botAnteFami;
    }

    public void setBotAnteFami(String botAnteFami) {
        this.botAnteFami = botAnteFami;
    }

    public String getBotCitaFinalizada() {
        return botCitaFinalizada;
    }

    public void setBotCitaFinalizada(String botCitaFinalizada) {
        this.botCitaFinalizada = botCitaFinalizada;
    }

    public String getBotAntcGenerales() {
        return botAntcGenerales;
    }

    public void setBotAntcGenerales(String botAntcGenerales) {
        this.botAntcGenerales = botAntcGenerales;
    }

    public String getBotDatCargo() {
        return botDatCargo;
    }

    public void setBotDatCargo(String botDatCargo) {
        this.botDatCargo = botDatCargo;
    }

    public String getBotAntGineco() {
        return botAntGineco;
    }

    public void setBotAntGineco(String botAntGineco) {
        this.botAntGineco = botAntGineco;
    }

    public String getBotGenero() {
        return botGenero;
    }

    public void setBotGenero(String botGenero) {
        this.botGenero = botGenero;
    }

    public String getBotnGenero() {
        return botnGenero;
    }

    public void setBotnGenero(String botnGenero) {
        this.botnGenero = botnGenero;
    }

    public String getBotToxAlerg() {
        return botToxAlerg;
    }

    public void setBotToxAlerg(String botToxAlerg) {
        this.botToxAlerg = botToxAlerg;
    }

    public String getBotExFisico() {
        return botExFisico;
    }

    public void setBotExFisico(String botExFisico) {
        this.botExFisico = botExFisico;
    }

    public String getBotExFisico1() {
        return botExFisico1;
    }

    public void setBotExFisico1(String botExFisico1) {
        this.botExFisico1 = botExFisico1;
    }

    public String getBotExFisico2() {
        return botExFisico2;
    }

    public void setBotExFisico2(String botExFisico2) {
        this.botExFisico2 = botExFisico2;
    }

    public String getBotExFisico3() {
        return botExFisico3;
    }

    public void setBotExFisico3(String botExFisico3) {
        this.botExFisico3 = botExFisico3;
    }

    public String getBotExFisico4() {
        return botExFisico4;
    }

    public void setBotExFisico4(String botExFisico4) {
        this.botExFisico4 = botExFisico4;
    }

    public String getBotExFisico5() {
        return botExFisico5;
    }

    public void setBotExFisico5(String botExFisico5) {
        this.botExFisico5 = botExFisico5;
    }

    public String getBotInmunizacion() {
        return botInmunizacion;
    }

    public void setBotInmunizacion(String botInmunizacion) {
        this.botInmunizacion = botInmunizacion;
    }

    public String getBotAntecOcupacionales() {
        return botAntecOcupacionales;
    }

    public void setBotAntecOcupacionales(String botAntecOcupacionales) {
        this.botAntecOcupacionales = botAntecOcupacionales;
    }

    public String getBotTrabajoActual() {
        return botTrabajoActual;
    }

    public void setBotTrabajoActual(String botTrabajoActual) {
        this.botTrabajoActual = botTrabajoActual;
    }

    public String getBotExamenIngreso() {
        return botExamenIngreso;
    }

    public void setBotExamenIngreso(String botExamenIngreso) {
        this.botExamenIngreso = botExamenIngreso;
    }

    public String getBotElementosProteccion() {
        return botElementosProteccion;
    }

    public void setBotElementosProteccion(String botElementosProteccion) {
        this.botElementosProteccion = botElementosProteccion;
    }

    public String getBotIncapacidades() {
        return botIncapacidades;
    }

    public void setBotIncapacidades(String botIncapacidades) {
        this.botIncapacidades = botIncapacidades;
    }

    public String getBotConsultaMedica() {
        return botConsultaMedica;
    }

    public void setBotConsultaMedica(String botConsultaMedica) {
        this.botConsultaMedica = botConsultaMedica;
    }

    public String getBotProcedimiento() {
        return botProcedimiento;
    }

    public void setBotProcedimiento(String botProcedimiento) {
        this.botProcedimiento = botProcedimiento;
    }

    public String getBotRecomMedicas() {
        return botRecomMedicas;
    }

    public void setBotRecomMedicas(String botRecomMedicas) {
        this.botRecomMedicas = botRecomMedicas;
    }

    public String getBotExamApoyDiagnostico() {
        return botExamApoyDiagnostico;
    }

    public void setBotExamApoyDiagnostico(String botExamApoyDiagnostico) {
        this.botExamApoyDiagnostico = botExamApoyDiagnostico;
    }

    public String getBotRecomMedicaOtros() {
        return botRecomMedicaOtros;
    }

    public void setBotRecomMedicaOtros(String botRecomMedicaOtros) {
        this.botRecomMedicaOtros = botRecomMedicaOtros;
    }

    public String getBotFinalizar() {
        return botFinalizar;
    }

    public void setBotFinalizar(String botFinalizar) {
        this.botFinalizar = botFinalizar;
    }

    public String getBotRevisionSistem() {
        return botRevisionSistem;
    }

    public void setBotRevisionSistem(String botRevisionSistem) {
        this.botRevisionSistem = botRevisionSistem;
    }

    public String getBotTalla() {
        return botTalla;
    }

    public void setBotTalla(String botTalla) {
        this.botTalla = botTalla;
    }

    public String getBotVista() {
        return botVista;
    }

    public void setBotVista(String botVista) {
        this.botVista = botVista;
    }

    public String getBotOrl() {
        return botOrl;
    }

    public void setBotOrl(String botOrl) {
        this.botOrl = botOrl;
    }

    public String getBotCuello() {
        return botCuello;
    }

    public void setBotCuello(String botCuello) {
        this.botCuello = botCuello;
    }

    public String getBotTorax() {
        return botTorax;
    }

    public void setBotTorax(String botTorax) {
        this.botTorax = botTorax;
    }

    public String getBotAparRespiratorio() {
        return botAparRespiratorio;
    }

    public void setBotAparRespiratorio(String botAparRespiratorio) {
        this.botAparRespiratorio = botAparRespiratorio;
    }

    public String getBotApatCardioVascular() {
        return botApatCardioVascular;
    }

    public void setBotApatCardioVascular(String botApatCardioVascular) {
        this.botApatCardioVascular = botApatCardioVascular;
    }

    public String getBotAparDigestivo() {
        return botAparDigestivo;
    }

    public void setBotAparDigestivo(String botAparDigestivo) {
        this.botAparDigestivo = botAparDigestivo;
    }

    public String getBotGenitoUrinario() {
        return botGenitoUrinario;
    }

    public void setBotGenitoUrinario(String botGenitoUrinario) {
        this.botGenitoUrinario = botGenitoUrinario;
    }

    public String getBotMusculoEsqueletico() {
        return botMusculoEsqueletico;
    }

    public void setBotMusculoEsqueletico(String botMusculoEsqueletico) {
        this.botMusculoEsqueletico = botMusculoEsqueletico;
    }

    public String getBotSistemaNervioso() {
        return botSistemaNervioso;
    }

    public void setBotSistemaNervioso(String botSistemaNervioso) {
        this.botSistemaNervioso = botSistemaNervioso;
    }

    public String getBotPieAnexos() {
        return botPieAnexos;
    }

    public void setBotPieAnexos(String botPieAnexos) {
        this.botPieAnexos = botPieAnexos;
    }

    public String getBotSistLinfatico() {
        return botSistLinfatico;
    }

    public void setBotSistLinfatico(String botSistLinfatico) {
        this.botSistLinfatico = botSistLinfatico;
    }

    public String getBotSisEndocrino() {
        return botSisEndocrino;
    }

    public void setBotSisEndocrino(String botSisEndocrino) {
        this.botSisEndocrino = botSisEndocrino;
    }

    public String getBotEstadoMental() {
        return botEstadoMental;
    }

    public void setBotEstadoMental(String botEstadoMental) {
        this.botEstadoMental = botEstadoMental;
    }

    public String getBotObservacionesRecomendaciones() {
        return botObservacionesRecomendaciones;
    }

    public void setBotObservacionesRecomendaciones(String botObservacionesRecomendaciones) {
        this.botObservacionesRecomendaciones = botObservacionesRecomendaciones;
    }

    public String getBotConcOcupacional() {
        return botConcOcupacional;
    }

    public void setBotConcOcupacional(String botConcOcupacional) {
        this.botConcOcupacional = botConcOcupacional;
    }

    public String getFacturada() {
        return facturada;
    }

    public void setFacturada(String facturada) {
        this.facturada = facturada;
    }

    public Integer getIngresoPerson() {
        return ingresoPerson;
    }

    public void setIngresoPerson(Integer ingresoPerson) {
        this.ingresoPerson = ingresoPerson;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Long getCodigoEpsSede() {
        return codigoEpsSede;
    }

    public void setCodigoEpsSede(Long codigoEpsSede) {
        this.codigoEpsSede = codigoEpsSede;
    }

    public String getCitaActiva() {
        return citaActiva;
    }

    public void setCitaActiva(String citaActiva) {
        this.citaActiva = citaActiva;
    }

    public String getCodigoDepartamentoRips() {
        return codigoDepartamentoRips;
    }

    public void setCodigoDepartamentoRips(String codigoDepartamentoRips) {
        this.codigoDepartamentoRips = codigoDepartamentoRips;
    }

    public String getCodigoMunicipioRip() {
        return codigoMunicipioRip;
    }

    public void setCodigoMunicipioRip(String codigoMunicipioRip) {
        this.codigoMunicipioRip = codigoMunicipioRip;
    }

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getClasificacionIdentificacion() {
        return clasificacionIdentificacion;
    }

    public void setClasificacionIdentificacion(String clasificacionIdentificacion) {
        this.clasificacionIdentificacion = clasificacionIdentificacion;
    }

    public String getCodigoEntidadPromotora() {
        return codigoEntidadPromotora;
    }

    public void setCodigoEntidadPromotora(String codigoEntidadPromotora) {
        this.codigoEntidadPromotora = codigoEntidadPromotora;
    }

    public TipoArchivoRips getCodigoRipsAH() {
        return codigoRipsAH;
    }

    public void setCodigoRipsAH(TipoArchivoRips codigoRipsAH) {
        this.codigoRipsAH = codigoRipsAH;
    }

    public TipoArchivoRips getCodigoRipsAN() {
        return codigoRipsAN;
    }

    public void setCodigoRipsAN(TipoArchivoRips codigoRipsAN) {
        this.codigoRipsAN = codigoRipsAN;
    }

    public TipoArchivoRips getCodigoRipsAT() {
        return codigoRipsAT;
    }

    public void setCodigoRipsAT(TipoArchivoRips codigoRipsAT) {
        this.codigoRipsAT = codigoRipsAT;
    }

    public TipoArchivoRips getCodigoRipsAC() {
        return codigoRipsAC;
    }

    public void setCodigoRipsAC(TipoArchivoRips codigoRipsAC) {
        this.codigoRipsAC = codigoRipsAC;
    }

    public TipoArchivoRips getCodigoRipsCT() {
        return codigoRipsCT;
    }

    public void setCodigoRipsCT(TipoArchivoRips codigoRipsCT) {
        this.codigoRipsCT = codigoRipsCT;
    }

    public TipoArchivoRips getCodigoRipsUS() {
        return codigoRipsUS;
    }

    public void setCodigoRipsUS(TipoArchivoRips codigoRipsUS) {
        this.codigoRipsUS = codigoRipsUS;
    }

    public TipoArchivoRips getCodigoRipsAP() {
        return codigoRipsAP;
    }

    public void setCodigoRipsAP(TipoArchivoRips codigoRipsAP) {
        this.codigoRipsAP = codigoRipsAP;
    }

    public TipoArchivoRips getCodigoRipsAU() {
        return codigoRipsAU;
    }

    public void setCodigoRipsAU(TipoArchivoRips codigoRipsAU) {
        this.codigoRipsAU = codigoRipsAU;
    }

    public TipoArchivoRips getCodigoRipsAF() {
        return codigoRipsAF;
    }

    public void setCodigoRipsAF(TipoArchivoRips codigoRipsAF) {
        this.codigoRipsAF = codigoRipsAF;
    }

    public TipoArchivoRips getCodigoRipsAM() {
        return codigoRipsAM;
    }

    public void setCodigoRipsAM(TipoArchivoRips codigoRipsAM) {
        this.codigoRipsAM = codigoRipsAM;
    }

    
    
    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Recomendacionesmedicamentos> getRecomendacionesmedicamentosList() {
        return recomendacionesmedicamentosList;
    }

    public void setRecomendacionesmedicamentosList(List<Recomendacionesmedicamentos> recomendacionesmedicamentosList) {
        this.recomendacionesmedicamentosList = recomendacionesmedicamentosList;
    }

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    @XmlTransient
    public List<FacturaRadicacion> getFacturaRadicacionList() {
        return facturaRadicacionList;
    }

    public void setFacturaRadicacionList(List<FacturaRadicacion> facturaRadicacionList) {
        this.facturaRadicacionList = facturaRadicacionList;
    }

    public Cargopersona getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Cargopersona codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public Area getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Area codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Tipoexamen getCodigoExamenRealizar() {
        return codigoExamenRealizar;
    }

    public void setCodigoExamenRealizar(Tipoexamen codigoExamenRealizar) {
        this.codigoExamenRealizar = codigoExamenRealizar;
    }

    public Upz getUpz() {
        return upz;
    }

    public void setUpz(Upz upz) {
        this.upz = upz;
    }

    public Religion getCodigoReligion() {
        return codigoReligion;
    }

    public void setCodigoReligion(Religion codigoReligion) {
        this.codigoReligion = codigoReligion;
    }

    public Usuario getCodigoProfesional() {
        return codigoProfesional;
    }

    public void setCodigoProfesional(Usuario codigoProfesional) {
        this.codigoProfesional = codigoProfesional;
    }

    public Usuario getRadicadoPor() {
        return radicadoPor;
    }

    public void setRadicadoPor(Usuario radicadoPor) {
        this.radicadoPor = radicadoPor;
    }

    public Tipogenero getGenero() {
        return genero;
    }

    public void setGenero(Tipogenero genero) {
        this.genero = genero;
    }

    public Arl getCodigoArl() {
        return codigoArl;
    }

    public void setCodigoArl(Arl codigoArl) {
        this.codigoArl = codigoArl;
    }

    public NivelEducativo getPrimaria() {
        return primaria;
    }

    public void setPrimaria(NivelEducativo primaria) {
        this.primaria = primaria;
    }

    public Barrio getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(Barrio codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public ClasificacionEps getClasificacionEps() {
        return clasificacionEps;
    }

    public void setClasificacionEps(ClasificacionEps clasificacionEps) {
        this.clasificacionEps = clasificacionEps;
    }

    public ClasificacionProfesionPersona getCodigoClasificacionProfesionPersona() {
        return codigoClasificacionProfesionPersona;
    }

    public void setCodigoClasificacionProfesionPersona(ClasificacionProfesionPersona codigoClasificacionProfesionPersona) {
        this.codigoClasificacionProfesionPersona = codigoClasificacionProfesionPersona;
    }

    public Tipoafiliacion getCodigoAfiliacion() {
        return codigoAfiliacion;
    }

    public void setCodigoAfiliacion(Tipoafiliacion codigoAfiliacion) {
        this.codigoAfiliacion = codigoAfiliacion;
    }

    public Estadofactura getCodigoestadoFactura() {
        return codigoestadoFactura;
    }

    public void setCodigoestadoFactura(Estadofactura codigoestadoFactura) {
        this.codigoestadoFactura = codigoestadoFactura;
    }

    public Localidad getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Localidad codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public Estadocivil getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(Estadocivil codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public Consultorio getCodigoConsultorio() {
        return codigoConsultorio;
    }

    public void setCodigoConsultorio(Consultorio codigoConsultorio) {
        this.codigoConsultorio = codigoConsultorio;
    }

    public Procedimientosede getCodigoExamen() {
        return codigoExamen;
    }

    public void setCodigoExamen(Procedimientosede codigoExamen) {
        this.codigoExamen = codigoExamen;
    }

    public Profesionpersona getCodigoProfesionPersona() {
        return codigoProfesionPersona;
    }

    public void setCodigoProfesionPersona(Profesionpersona codigoProfesionPersona) {
        this.codigoProfesionPersona = codigoProfesionPersona;
    }

    public Datospersona getCodigoPersonaCita() {
        return codigoPersonaCita;
    }

    public void setCodigoPersonaCita(Datospersona codigoPersonaCita) {
        this.codigoPersonaCita = codigoPersonaCita;
    }

    public Municipio getCodigoMunicipioExpedicion() {
        return codigoMunicipioExpedicion;
    }

    public void setCodigoMunicipioExpedicion(Municipio codigoMunicipioExpedicion) {
        this.codigoMunicipioExpedicion = codigoMunicipioExpedicion;
    }

    public Dependencia getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(Dependencia codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public Municipio getCodigoMunicipioResidencia() {
        return codigoMunicipioResidencia;
    }

    public void setCodigoMunicipioResidencia(Municipio codigoMunicipioResidencia) {
        this.codigoMunicipioResidencia = codigoMunicipioResidencia;
    }

    public Municipio getCodigoMunicipioNacimiento() {
        return codigoMunicipioNacimiento;
    }

    public void setCodigoMunicipioNacimiento(Municipio codigoMunicipioNacimiento) {
        this.codigoMunicipioNacimiento = codigoMunicipioNacimiento;
    }

    public Empresa getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Empresa codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Pensiones getCodigoPensiones() {
        return codigoPensiones;
    }

    public void setCodigoPensiones(Pensiones codigoPensiones) {
        this.codigoPensiones = codigoPensiones;
    }

    public Tipoidentificacion getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public void setCodigoIdentificacion(Tipoidentificacion codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Sucursalempresa getSucursalEmpresa() {
        return sucursalEmpresa;
    }

    public void setSucursalEmpresa(Sucursalempresa sucursalEmpresa) {
        this.sucursalEmpresa = sucursalEmpresa;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public NivelEducativo getSecundaria() {
        return secundaria;
    }

    public void setSecundaria(NivelEducativo secundaria) {
        this.secundaria = secundaria;
    }

    @XmlTransient
    public List<RecomendacionesMedicasOtras> getRecomendacionesMedicasOtrasList() {
        return recomendacionesMedicasOtrasList;
    }

    public void setRecomendacionesMedicasOtrasList(List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList) {
        this.recomendacionesMedicasOtrasList = recomendacionesMedicasOtrasList;
    }

    @XmlTransient
    public List<Examenfisico> getExamenfisicoList() {
        return examenfisicoList;
    }

    public void setExamenfisicoList(List<Examenfisico> examenfisicoList) {
        this.examenfisicoList = examenfisicoList;
    }

    @XmlTransient
    public List<Formulamedica> getFormulamedicaList() {
        return formulamedicaList;
    }

    public void setFormulamedicaList(List<Formulamedica> formulamedicaList) {
        this.formulamedicaList = formulamedicaList;
    }

    @XmlTransient
    public List<Imprimir> getImprimirList() {
        return imprimirList;
    }

    public void setImprimirList(List<Imprimir> imprimirList) {
        this.imprimirList = imprimirList;
    }

    @XmlTransient
    public List<RadicacionFacturas> getRadicacionFacturasList() {
        return radicacionFacturasList;
    }

    public void setRadicacionFacturasList(List<RadicacionFacturas> radicacionFacturasList) {
        this.radicacionFacturasList = radicacionFacturasList;
    }

    @XmlTransient
    public List<Procedimiento> getProcedimientoList() {
        return procedimientoList;
    }

    public void setProcedimientoList(List<Procedimiento> procedimientoList) {
        this.procedimientoList = procedimientoList;
    }

    @XmlTransient
    public List<VacunacionPersona> getVacunacionPersonaList() {
        return vacunacionPersonaList;
    }

    public void setVacunacionPersonaList(List<VacunacionPersona> vacunacionPersonaList) {
        this.vacunacionPersonaList = vacunacionPersonaList;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList() {
        return examenesApoyoDiagnosticoList;
    }

    public void setExamenesApoyoDiagnosticoList(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList) {
        this.examenesApoyoDiagnosticoList = examenesApoyoDiagnosticoList;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList1() {
        return examenesApoyoDiagnosticoList1;
    }

    public void setExamenesApoyoDiagnosticoList1(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList1) {
        this.examenesApoyoDiagnosticoList1 = examenesApoyoDiagnosticoList1;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList() {
        return diagnosticoIngresoList;
    }

    public void setDiagnosticoIngresoList(List<DiagnosticoIngreso> diagnosticoIngresoList) {
        this.diagnosticoIngresoList = diagnosticoIngresoList;
    }

    @XmlTransient
    public List<Consultamedica> getConsultamedicaList() {
        return consultamedicaList;
    }

    public void setConsultamedicaList(List<Consultamedica> consultamedicaList) {
        this.consultamedicaList = consultamedicaList;
    }

    public TipoUsuario getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(TipoUsuario codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public TipoEdad getCodigoTipoEdad() {
        return codigoTipoEdad;
    }

    public void setCodigoTipoEdad(TipoEdad codigoTipoEdad) {
        this.codigoTipoEdad = codigoTipoEdad;
    }

    public ZonaResidencia getCodigoZonaResidencia() {
        return codigoZonaResidencia;
    }

    public void setCodigoZonaResidencia(ZonaResidencia codigoZonaResidencia) {
        this.codigoZonaResidencia = codigoZonaResidencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCitaPersona != null ? codigoCitaPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citapersona)) {
            return false;
        }
        Citapersona other = (Citapersona) object;
        if ((this.codigoCitaPersona == null && other.codigoCitaPersona != null) || (this.codigoCitaPersona != null && !this.codigoCitaPersona.equals(other.codigoCitaPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Citapersona[ codigoCitaPersona=" + codigoCitaPersona + " ]";
    }

}
