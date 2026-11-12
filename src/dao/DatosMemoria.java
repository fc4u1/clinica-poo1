package dao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.*;
import util.SeguridadClave;
import util.Validaciones;

public class DatosMemoria implements RepositorioClinica {
    private final ArrayList<Usuario> listaUsuarios=new ArrayList<>();
    private final ArrayList<Rol> listaRoles=new ArrayList<>();
    private final ArrayList<Empleado> listaEmpleados=new ArrayList<>();
    private final ArrayList<Paciente> listaPacientes=new ArrayList<>();
    private final ArrayList<Medico> listaMedicos=new ArrayList<>();
    private final ArrayList<Cita> listaCitas=new ArrayList<>();
    private final ArrayList<Medicamento> listaMedicamentos=new ArrayList<>();
    private final ArrayList<Lote> listaLotes=new ArrayList<>();
    private final ArrayList<Almacen> listaAlmacenes=new ArrayList<>();
    private final ArrayList<Movimiento> listaMovimientos=new ArrayList<>();
    private final ArrayList<int[]> gruposMovimientos=new ArrayList<>();
    private final ArrayList<Proveedor> listaProveedores=new ArrayList<>();
    private final ArrayList<int[]> asignaciones=new ArrayList<>();
    private final ArrayList<HistorialMedico> listaHistoriales=new ArrayList<>();
    private final ArrayList<Consulta> listaConsultas=new ArrayList<>();
    private final ArrayList<Receta> listaRecetas=new ArrayList<>();
    private final ArrayList<Seguro> listaSeguros=new ArrayList<>();
    private final ArrayList<PolizaPaciente> listaPolizas=new ArrayList<>();
    private final ArrayList<HorarioMedico> listaHorarios=new ArrayList<>();
    private int sigConsulta=1,sigReceta=1,sigSeguro=3,sigPoliza=2,sigHorario=1;
    private int sigGrupo=1;
    private int sigPaciente=4,sigMedico=4,sigCita=3,sigMedicamento=4,sigLote=4,sigMovimiento=1,sigProveedor=3,sigAlmacen=2,sigUsuario=6;
    public DatosMemoria(){
        listaRoles.add(new Rol(1,"Administrador"));listaRoles.add(new Rol(2,"Recepcionista"));
        listaRoles.add(new Rol(3,"Médico"));listaRoles.add(new Rol(4,"Farmacia"));
        listaEmpleados.add(new Empleado(1,"90000001","Alicia","Torres"));
        listaEmpleados.add(new Empleado(2,"90000002","Bruno","Salazar"));
        listaEmpleados.add(new Empleado(3,"90000003","Carla","Mendoza"));
        listaEmpleados.add(new Empleado(4,"90000004","Diego","Ramos"));
        listaEmpleados.add(new Empleado(5,"90000005","Elena","Paz"));
        String[] nombres={"admin01","recepcion01","medico01","farmacia01","recepcion02"};
        int[] roles={1,2,3,4,2};
        for(int i=0;i<5;i++){
            listaUsuarios.add(new Usuario(i+1,nombres[i],SeguridadClave.crear("demo123"),
                roles[i],listaRoles.get(roles[i]-1).getNombre(),i+1,true));
        }
        listaPacientes.add(new Paciente(1,"70000001","Lucía","Paredes",LocalDate.of(1998,6,15),"987111111"));
        listaPacientes.add(new Paciente(2,"70000002","Mario","Ríos",LocalDate.of(1985,3,20),"987222222"));
        listaPacientes.add(new Paciente(3,"70000003","Ana","Campos",LocalDate.of(2001,9,9),"987333333"));
        listaMedicos.add(new Medico(1,"80000001","Rosa","Aguilar","Medicina general"));
        listaMedicos.add(new Medico(2,"80000002","Carlos","Vega","Pediatría"));
        listaMedicos.add(new Medico(3,"80000003","Miriam","Flores","Cardiología"));
        listaCitas.add(new Cita(1,1,1,2,LocalDate.now().plusDays(1),LocalTime.of(9,0),"Programada"));
        listaCitas.add(new Cita(2,2,2,2,LocalDate.now().plusDays(2),LocalTime.of(11,30),"Programada"));
        listaMedicamentos.add(new Medicamento(1,"Paracetamol 500mg","Paracetamol","Tableta",20));
        listaMedicamentos.add(new Medicamento(2,"Ibuprofeno 400mg","Ibuprofeno","Tableta",15));
        listaMedicamentos.add(new Medicamento(3,"Amoxicilina 500mg","Amoxicilina","Cápsula",10));
        listaAlmacenes.add(new Almacen(1,"Farmacia principal",1000));
        listaProveedores.add(new Proveedor(1,"20123456789","Laboratorio Nacional","014445555"));
        listaProveedores.add(new Proveedor(2,"20987654321","Distribuidora Salud","015556666"));
        listaHistoriales.add(new HistorialMedico(1,"O+","Ninguna","Ninguna","Control preventivo"));
        asignaciones.add(new int[]{4,1});
        listaLotes.add(new Lote(1,1,1,1,LocalDate.now(),LocalDate.now().plusMonths(12),50));
        listaLotes.add(new Lote(2,2,1,2,LocalDate.now(),LocalDate.now().plusMonths(8),40));
        listaLotes.add(new Lote(3,3,1,1,LocalDate.now(),LocalDate.now().plusMonths(3),30));
        listaSeguros.add(new Seguro(1,"Aseguradora Ejemplo","Básico","010000001"));
        listaSeguros.add(new Seguro(2,"Aseguradora Demostración","Integral","010000002"));
        listaPolizas.add(new PolizaPaciente(1,1,1,"POL-0001",LocalDate.now().minusMonths(2),LocalDate.now().plusMonths(10)));
    }
    @Override public Usuario autenticar(String usuario,String clave){
        for(Usuario u:listaUsuarios)if(u.isActivo()&&u.getUsuario().equalsIgnoreCase(usuario.trim())
                &&SeguridadClave.verificar(clave,u.getClaveHash()))return u;
        return null;
    }
    @Override public ArrayList<Usuario> usuarios(){return new ArrayList<>(listaUsuarios);}
    @Override public ArrayList<Rol> roles(){return new ArrayList<>(listaRoles);}
    @Override public ArrayList<Empleado> empleados(){return new ArrayList<>(listaEmpleados);}
    @Override public void crearUsuario(String usuario,String clave,int rol,int empleado){
        Validaciones.obligatorio(usuario,"Usuario");Validaciones.obligatorio(clave,"Contraseña");
        if(clave.length()<6)throw new IllegalArgumentException("La clave debe tener al menos 6 caracteres.");
        for(Usuario u:listaUsuarios)if(u.getUsuario().equalsIgnoreCase(usuario))throw new IllegalArgumentException("Usuario duplicado.");
        boolean rolValido=false;String nombreRol="";
        for(Rol r:listaRoles)if(r.getId()==rol){rolValido=true;nombreRol=r.getNombre();}
        boolean empleadoValido=false;
        for(Empleado e:listaEmpleados)if(e.getId()==empleado)empleadoValido=true;
        if(!rolValido||!empleadoValido)throw new IllegalArgumentException("Rol o empleado inexistente.");
        for(Usuario u:listaUsuarios)if(u.getIdEmpleado()==empleado)throw new IllegalArgumentException("El empleado ya tiene usuario.");
        listaUsuarios.add(new Usuario(sigUsuario++,usuario,SeguridadClave.crear(clave),rol,nombreRol,empleado,true));
    }
    @Override public ArrayList<Paciente> pacientes(){return new ArrayList<>(listaPacientes);}
    @Override public Paciente guardarPaciente(Paciente p){
        Validaciones.dni(p.getDni());Validaciones.obligatorio(p.getNombres(),"Nombres");
        Validaciones.obligatorio(p.getApellidos(),"Apellidos");
        if(p.getFechaNacimiento()==null||p.getFechaNacimiento().isAfter(LocalDate.now()))throw new IllegalArgumentException("Fecha de nacimiento inválida.");
        for(Paciente otro:listaPacientes)if(otro.getDni().equals(p.getDni())&&otro.getId()!=p.getId())
            throw new IllegalArgumentException("El DNI ya está registrado.");
        if(p.getId()==0){p.setId(sigPaciente++);listaPacientes.add(p);return p;}
        for(int i=0;i<listaPacientes.size();i++)if(listaPacientes.get(i).getId()==p.getId()){
            listaPacientes.set(i,p);return p;
        }
        throw new IllegalArgumentException("Paciente no encontrado.");
    }
    @Override public ArrayList<Medico> medicos(){return new ArrayList<>(listaMedicos);}
    @Override public ArrayList<Cita> citas(){return new ArrayList<>(listaCitas);}
    @Override public Cita guardarCita(Cita c){
        boolean paciente=false,medico=false,empleado=false;
        for(Paciente p:listaPacientes)if(p.getId()==c.getIdPaciente())paciente=true;
        for(Medico m:listaMedicos)if(m.getId()==c.getIdMedico())medico=true;
        for(Empleado e:listaEmpleados)if(e.getId()==c.getIdEmpleado())empleado=true;
        if(!paciente||!medico||!empleado)throw new IllegalArgumentException("Paciente, médico o empleado no existe.");
        if(c.getFecha()==null||c.getHora()==null||c.getFecha().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Indica una fecha y hora de cita válidas.");
        boolean hayHorarios=false,coincide=false;
        for(HorarioMedico h:listaHorarios)if(h.getIdMedico()==c.getIdMedico()&&h.getFecha().equals(c.getFecha())){
            hayHorarios=true;
            if(!c.getHora().isBefore(h.getInicio())&&c.getHora().isBefore(h.getFin()))coincide=true;
        }
        if(hayHorarios&&!coincide)throw new IllegalArgumentException("La cita está fuera del horario registrado del médico.");
        for(Cita otra:listaCitas){
            if(otra.getId()!=c.getId()&&otra.getIdMedico()==c.getIdMedico()
               &&otra.getFecha().equals(c.getFecha())&&otra.getHora().equals(c.getHora())
               &&otra.getEstado().equals("Programada"))throw new IllegalArgumentException("El médico ya tiene una cita en ese horario.");
        }
        if(c.getId()==0){c.setId(sigCita++);listaCitas.add(c);return c;}
        for(int i=0;i<listaCitas.size();i++)if(listaCitas.get(i).getId()==c.getId()){
            if(!listaCitas.get(i).getEstado().equals("Programada"))throw new IllegalArgumentException("Solo se puede modificar una cita programada.");
            listaCitas.set(i,c);return c;
        }
        throw new IllegalArgumentException("Cita no encontrada.");
    }
    @Override public void cancelarCita(int id){
        for(Cita c:listaCitas)if(c.getId()==id){
            if(!c.getEstado().equals("Programada"))throw new IllegalArgumentException("La cita ya no está programada.");
            c.cancelar();return;
        }
        throw new IllegalArgumentException("Cita no encontrada.");
    }
    @Override public ArrayList<Medicamento> medicamentos(){return new ArrayList<>(listaMedicamentos);}
    @Override public Medicamento guardarMedicamento(Medicamento m){
        Validaciones.obligatorio(m.getNombreComercial(),"Nombre comercial");
        Validaciones.obligatorio(m.getNombreGenerico(),"Nombre genérico");
        if(m.getStockMinimo()<0)throw new IllegalArgumentException("Stock mínimo negativo.");
        if(m.getId()==0){m.setId(sigMedicamento++);listaMedicamentos.add(m);return m;}
        for(int i=0;i<listaMedicamentos.size();i++)if(listaMedicamentos.get(i).getId()==m.getId()){
            listaMedicamentos.set(i,m);return m;
        }
        throw new IllegalArgumentException("Medicamento no encontrado.");
    }
    @Override public ArrayList<Lote> lotes(){return new ArrayList<>(listaLotes);}
    @Override public Lote agregarLote(int idMed,int idAlmacen,int idProveedor,LocalDate vencimiento,int cantidad){
        if(cantidad<0||vencimiento==null||vencimiento.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Cantidad o vencimiento del lote inválido.");
        boolean med=false,alm=false;
        for(Medicamento m:listaMedicamentos)if(m.getId()==idMed)med=true;
        for(Almacen a:listaAlmacenes)if(a.getId()==idAlmacen)alm=true;
        if(!med||!alm)throw new IllegalArgumentException("Medicamento o almacén inexistente.");
        boolean proveedor=false;
        for(Proveedor p:listaProveedores)if(p.getId()==idProveedor)proveedor=true;
        if(!proveedor)throw new IllegalArgumentException("Selecciona un proveedor válido.");
        Lote lote=new Lote(sigLote++,idMed,idAlmacen,idProveedor,LocalDate.now(),vencimiento,cantidad);
        listaLotes.add(lote);return lote;
    }
    @Override public ArrayList<Almacen> almacenes(){return new ArrayList<>(listaAlmacenes);}
    @Override public ArrayList<Movimiento> movimientos(){return new ArrayList<>(listaMovimientos);}
    @Override public int grupoMovimiento(int idMovimiento){
        for(int[] relacion:gruposMovimientos)if(relacion[0]==idMovimiento)return relacion[1];
        throw new IllegalArgumentException("Movimiento no encontrado en ningún grupo.");
    }
    @Override public void registrarMovimiento(int idLote,int idEmpleado,String tipo,int cantidad){
        if(cantidad<=0)throw new IllegalArgumentException("Cantidad debe ser positiva.");
        if(!tipo.equals("ENTRADA")&&!tipo.equals("SALIDA"))throw new IllegalArgumentException("Tipo inválido.");
        boolean empleado=false;
        for(Empleado e:listaEmpleados)if(e.getId()==idEmpleado)empleado=true;
        if(!empleado)throw new IllegalArgumentException("Empleado no identificado.");
        for(Lote l:listaLotes)if(l.getId()==idLote){
            if(tipo.equals("SALIDA")&&(cantidad>l.getCantidad()||l.getFechaVencimiento().isBefore(LocalDate.now())))
                throw new IllegalArgumentException("Stock insuficiente o lote vencido.");
            l.modificarStock(l.getCantidad()+(tipo.equals("ENTRADA")?cantidad:-cantidad));
            Movimiento movimiento=new Movimiento(sigMovimiento++,idLote,idEmpleado,tipo,LocalDateTime.now(),cantidad);
            listaMovimientos.add(movimiento);
            gruposMovimientos.add(new int[]{movimiento.getId(),sigGrupo++});
            return;
        }
        throw new IllegalArgumentException("Lote no encontrado.");
    }

    @Override public Paciente buscarPacienteDni(String dni){
        for(Paciente p:listaPacientes)if(p.getDni().equals(dni.trim()))return p;
        return null;
    }
    @Override public Medico buscarMedicoDni(String dni){
        for(Medico m:listaMedicos)if(m.getDni().equals(dni.trim()))return m;
        return null;
    }
    @Override public Medico guardarMedico(Medico medico){
        Validaciones.dni(medico.getDni());Validaciones.obligatorio(medico.getNombres(),"Nombres");
        Validaciones.obligatorio(medico.getApellidos(),"Apellidos");
        Validaciones.obligatorio(medico.getEspecialidad(),"Especialidad");
        for(Medico m:listaMedicos)if(m.getDni().equals(medico.getDni())&&m.getId()!=medico.getId())
            throw new IllegalArgumentException("Ya existe un médico con ese DNI.");
        if(medico.getId()==0){medico.setId(sigMedico++);listaMedicos.add(medico);return medico;}
        for(int i=0;i<listaMedicos.size();i++)if(listaMedicos.get(i).getId()==medico.getId()){
            listaMedicos.set(i,medico);return medico;
        }
        throw new IllegalArgumentException("Médico no encontrado.");
    }
    @Override public void eliminarPaciente(int id){
        for(PolizaPaciente poliza:listaPolizas)if(poliza.getIdPaciente()==id)
            throw new IllegalArgumentException("El paciente tiene una póliza registrada; conserve su información.");
        for(Cita c:listaCitas)if(c.getIdPaciente()==id)throw new IllegalArgumentException("El paciente tiene citas registradas y no se puede eliminar. Conserve su historial.");
        for(int i=0;i<listaPacientes.size();i++)if(listaPacientes.get(i).getId()==id){
            listaPacientes.remove(i);listaHistoriales.removeIf(h->h.getIdPaciente()==id);return;
        }
        throw new IllegalArgumentException("Paciente no encontrado.");
    }
    @Override public void eliminarMedico(int id){
        for(HorarioMedico h:listaHorarios)if(h.getIdMedico()==id)
            throw new IllegalArgumentException("El médico tiene horarios registrados.");
        for(Cita c:listaCitas)if(c.getIdMedico()==id)throw new IllegalArgumentException("No se puede eliminar: el médico tiene citas asociadas.");
        for(int i=0;i<listaMedicos.size();i++)if(listaMedicos.get(i).getId()==id){listaMedicos.remove(i);return;}
        throw new IllegalArgumentException("Médico no encontrado.");
    }
    @Override public void eliminarCita(int id){
        for(Consulta consulta:listaConsultas)if(consulta.getIdCita()==id)
            throw new IllegalArgumentException("No se puede eliminar una cita que ya tiene una consulta médica registrada.");
        for(Cita c:listaCitas)if(c.getId()==id){
            if(c.getEstado().equals("Eliminada"))throw new IllegalArgumentException("La cita ya está eliminada.");
            c.eliminar();return;
        }
        throw new IllegalArgumentException("Cita no encontrada.");
    }
    @Override public void editarUsuario(int id,String nombre,String claveNueva){
        Validaciones.obligatorio(nombre,"Nombre de usuario");
        for(Usuario u:listaUsuarios)if(u.getId()!=id&&u.getUsuario().equalsIgnoreCase(nombre.trim()))
            throw new IllegalArgumentException("Ese nombre de usuario ya existe.");
        for(Usuario u:listaUsuarios)if(u.getId()==id){
            u.actualizarNombre(nombre.trim());
            if(!claveNueva.isBlank()){
                if(claveNueva.length()<6)throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
                u.cambiarClaveHash(SeguridadClave.crear(claveNueva));
            }
            return;
        }
        throw new IllegalArgumentException("Usuario no encontrado.");
    }
    @Override public void activarUsuario(int id,boolean activo){
        for(Usuario u:listaUsuarios)if(u.getId()==id){u.setActivo(activo);return;}
        throw new IllegalArgumentException("Usuario no encontrado.");
    }
    @Override public void cambiarRolUsuario(int idUsuario,int idRol){
        Rol rol=null;
        for(Rol r:listaRoles)if(r.getId()==idRol)rol=r;
        if(rol==null)throw new IllegalArgumentException("Rol no encontrado.");
        for(Usuario u:listaUsuarios)if(u.getId()==idUsuario){u.asignarRol(rol.getId(),rol.getNombre());return;}
        throw new IllegalArgumentException("Usuario no encontrado.");
    }
    @Override public Almacen guardarAlmacen(Almacen a){
        Validaciones.obligatorio(a.getNombre(),"Nombre del almacén");
        if(a.getCapacidad()<=0)throw new IllegalArgumentException("La capacidad debe ser positiva.");
        if(a.getId()==0){a.setId(sigAlmacen++);listaAlmacenes.add(a);return a;}
        for(int i=0;i<listaAlmacenes.size();i++)if(listaAlmacenes.get(i).getId()==a.getId()){listaAlmacenes.set(i,a);return a;}
        throw new IllegalArgumentException("Almacén no encontrado.");
    }

    @Override public ArrayList<Empleado> empleadosAsignados(int almacen){
        ArrayList<Empleado> result=new ArrayList<>();
        for(int[] par:asignaciones)if(par[1]==almacen)for(Empleado e:listaEmpleados)if(e.getId()==par[0])result.add(e);
        return result;
    }
    @Override public void asignarEmpleado(int empleado,int almacen){
        boolean e=false,a=false;
        for(Empleado x:listaEmpleados)if(x.getId()==empleado)e=true;
        for(Almacen x:listaAlmacenes)if(x.getId()==almacen)a=true;
        if(!e||!a)throw new IllegalArgumentException("Empleado o almacén no encontrado.");
        for(int[] par:asignaciones)if(par[0]==empleado&&par[1]==almacen)throw new IllegalArgumentException("Este empleado ya está asignado a ese almacén.");
        asignaciones.add(new int[]{empleado,almacen});
    }
    @Override public void quitarAsignacion(int empleado,int almacen){
        for(int i=0;i<asignaciones.size();i++)if(asignaciones.get(i)[0]==empleado&&asignaciones.get(i)[1]==almacen){asignaciones.remove(i);return;}
        throw new IllegalArgumentException("La asignación seleccionada no existe.");
    }

    @Override public void eliminarAlmacen(int id){
        for(Lote l:listaLotes)if(l.getIdAlmacen()==id)throw new IllegalArgumentException("No se elimina un almacén con lotes asociados.");
        for(int[] par:asignaciones)if(par[1]==id)throw new IllegalArgumentException("Retire las asignaciones de empleados antes de eliminar el almacén.");
        for(int i=0;i<listaAlmacenes.size();i++)if(listaAlmacenes.get(i).getId()==id){listaAlmacenes.remove(i);return;}
        throw new IllegalArgumentException("Almacén no encontrado.");
    }
    @Override public void actualizarLote(int id,int cantidad,LocalDate vencimiento){
        if(cantidad<0||vencimiento==null)throw new IllegalArgumentException("Cantidad o vencimiento inválidos.");
        for(int i=0;i<listaLotes.size();i++)if(listaLotes.get(i).getId()==id){
            Lote anterior=listaLotes.get(i);
            for(Movimiento m:listaMovimientos)if(m.getIdLote()==id)throw new IllegalArgumentException("Lote con movimientos: modifique el stock mediante entrada o salida.");
            listaLotes.set(i,new Lote(id,anterior.getIdMedicamento(),anterior.getIdAlmacen(),anterior.getIdProveedor(),anterior.getFechaIngreso(),vencimiento,cantidad));return;
        }
        throw new IllegalArgumentException("Lote no encontrado.");
    }
    @Override public void eliminarLote(int id){
        for(Movimiento m:listaMovimientos)if(m.getIdLote()==id)throw new IllegalArgumentException("No se puede eliminar un lote con movimientos.");
        for(int i=0;i<listaLotes.size();i++)if(listaLotes.get(i).getId()==id){listaLotes.remove(i);return;}
        throw new IllegalArgumentException("Lote no encontrado.");
    }
    @Override public void eliminarMedicamento(int id){
        for(Receta receta:listaRecetas)for(DetalleReceta detalle:receta.getDetalles())
            if(detalle.getIdMedicamento()==id)
                throw new IllegalArgumentException("No se elimina un medicamento que figura en una receta.");
        for(Lote l:listaLotes)if(l.getIdMedicamento()==id)throw new IllegalArgumentException("No se puede eliminar: tiene lotes asociados.");
        for(int i=0;i<listaMedicamentos.size();i++)if(listaMedicamentos.get(i).getId()==id){listaMedicamentos.remove(i);return;}
        throw new IllegalArgumentException("Medicamento no encontrado.");
    }
    @Override public ArrayList<Proveedor> proveedores(){return new ArrayList<>(listaProveedores);}
    @Override public Proveedor guardarProveedor(Proveedor p){
        String ruc=Validaciones.obligatorio(p.getRuc(),"RUC");
        if(!ruc.matches("[0-9]{11}"))throw new IllegalArgumentException("El RUC debe contener 11 dígitos.");
        Validaciones.obligatorio(p.getEmpresa(),"Empresa");
        for(Proveedor q:listaProveedores)if(q.getId()!=p.getId()&&q.getRuc().equals(ruc))throw new IllegalArgumentException("RUC duplicado.");
        if(p.getId()==0){p.setId(sigProveedor++);listaProveedores.add(p);return p;}
        for(int i=0;i<listaProveedores.size();i++)if(listaProveedores.get(i).getId()==p.getId()){listaProveedores.set(i,p);return p;}
        throw new IllegalArgumentException("Proveedor no encontrado.");
    }
    @Override public void eliminarProveedor(int id){
        for(Lote l:listaLotes)if(l.getIdProveedor()==id)throw new IllegalArgumentException("Proveedor con lotes asociados, no puede eliminarse.");
        for(int i=0;i<listaProveedores.size();i++)if(listaProveedores.get(i).getId()==id){listaProveedores.remove(i);return;}
        throw new IllegalArgumentException("Proveedor no encontrado.");
    }
    @Override public ArrayList<HistorialMedico> historiales(){return new ArrayList<>(listaHistoriales);}
    @Override public HistorialMedico guardarHistorial(HistorialMedico h){
        boolean existePaciente=false;
        for(Paciente p:listaPacientes)if(p.getId()==h.getIdPaciente())existePaciente=true;
        if(!existePaciente)throw new IllegalArgumentException("Paciente no encontrado.");
        Validaciones.obligatorio(h.getSangre(),"Grupo sanguíneo");
        for(int i=0;i<listaHistoriales.size();i++)if(listaHistoriales.get(i).getIdPaciente()==h.getIdPaciente()){
            listaHistoriales.set(i,h);return h;
        }
        listaHistoriales.add(h);return h;
    }

    @Override public void registrarMovimientosLotes(ArrayList<int[]> detalles,int idEmpleado,String tipo){
        if(detalles==null||detalles.isEmpty())throw new IllegalArgumentException("No se agregaron lotes al movimiento.");
        if(!tipo.equals("ENTRADA")&&!tipo.equals("SALIDA"))throw new IllegalArgumentException("Tipo inválido.");
        boolean empleado=false;for(Empleado e:listaEmpleados)if(e.getId()==idEmpleado)empleado=true;
        if(!empleado)throw new IllegalArgumentException("Empleado no encontrado.");
        ArrayList<Lote> lotesValidos=new ArrayList<>();
        for(int[] d:detalles){
            if(d==null||d.length<2||d[1]<=0)throw new IllegalArgumentException("Cantidad inválida.");
            Lote encontrado=null;
            for(Lote l:listaLotes)if(l.getId()==d[0])encontrado=l;
            if(encontrado==null)throw new IllegalArgumentException("Lote inexistente: "+d[0]);
            if(lotesValidos.contains(encontrado))throw new IllegalArgumentException("El lote se repitió en el movimiento.");
            if(tipo.equals("SALIDA")&&(encontrado.getCantidad()<d[1]||encontrado.getFechaVencimiento().isBefore(LocalDate.now())))
                throw new IllegalArgumentException("Stock insuficiente o lote vencido: "+d[0]);
            lotesValidos.add(encontrado);
        }
        int grupoActual=sigGrupo++;
        for(int i=0;i<detalles.size();i++){
            int[] d=detalles.get(i);Lote lote=lotesValidos.get(i);
            lote.modificarStock(lote.getCantidad()+(tipo.equals("ENTRADA")?d[1]:-d[1]));
            Movimiento movimiento=new Movimiento(sigMovimiento++,d[0],idEmpleado,tipo,LocalDateTime.now(),d[1]);
            listaMovimientos.add(movimiento);
            gruposMovimientos.add(new int[]{movimiento.getId(),grupoActual});
        }
        sigGrupo++;
    }


    @Override public ArrayList<Consulta> consultas(){return new ArrayList<>(listaConsultas);}
    @Override public void atenderCita(int idCita){
        for(Cita c:listaCitas)if(c.getId()==idCita){
            if(!c.getEstado().equals("Programada"))throw new IllegalArgumentException("Solo puede atenderse una cita programada.");
            c.setEstado("Atendida");return;
        }
        throw new IllegalArgumentException("Cita no encontrada.");
    }
    @Override public Consulta guardarConsulta(int idCita,String sintomas,String diagnostico,String tratamiento){
        Validaciones.obligatorio(sintomas,"Síntomas");Validaciones.obligatorio(diagnostico,"Diagnóstico");
        Validaciones.obligatorio(tratamiento,"Tratamiento");
        boolean valida=false;
        for(Cita c:listaCitas)if(c.getId()==idCita && c.getEstado().equals("Atendida"))valida=true;
        if(!valida)throw new IllegalArgumentException("Solo se registra una consulta a partir de una cita atendida.");
        for(Consulta c:listaConsultas)if(c.getIdCita()==idCita){
            c.actualizar(sintomas.trim(),diagnostico.trim(),tratamiento.trim());return c;
        }
        Consulta c=new Consulta(sigConsulta++,idCita,sintomas.trim(),diagnostico.trim(),tratamiento.trim());
        listaConsultas.add(c);return c;
    }
    @Override public ArrayList<Receta> recetas(){return new ArrayList<>(listaRecetas);}
    @Override public Receta crearReceta(int idConsulta,ArrayList<DetalleReceta> detalles){
        boolean consulta=false;
        for(Consulta c:listaConsultas)if(c.getId()==idConsulta)consulta=true;
        if(!consulta)throw new IllegalArgumentException("Consulta inexistente.");
        if(detalles==null || detalles.isEmpty())throw new IllegalArgumentException("La receta debe tener al menos un medicamento.");
        ArrayList<Integer> usados=new ArrayList<>();
        for(DetalleReceta d:detalles){
            if(d==null || d.getCantidad()<=0)throw new IllegalArgumentException("Cantidad de medicamento inválida.");
            if(usados.contains(d.getIdMedicamento()))throw new IllegalArgumentException("Un medicamento se repite en la receta.");
            usados.add(d.getIdMedicamento());
            boolean med=false;for(Medicamento m:listaMedicamentos)if(m.getId()==d.getIdMedicamento())med=true;
            if(!med)throw new IllegalArgumentException("El medicamento seleccionado no existe.");
        }
        Receta r=new Receta(sigReceta++,idConsulta,detalles);listaRecetas.add(r);return r;
    }
    @Override public void despacharReceta(int idReceta,int idEmpleado){
        Receta receta=null;
        for(Receta r:listaRecetas)if(r.getId()==idReceta)receta=r;
        if(receta==null)throw new IllegalArgumentException("Receta no encontrada.");
        if(!receta.getEstado().equals("Pendiente"))throw new IllegalArgumentException("Esta receta ya fue dispensada.");
        boolean empleado=false;for(Empleado e:listaEmpleados)if(e.getId()==idEmpleado)empleado=true;
        if(!empleado)throw new IllegalArgumentException("Empleado responsable inexistente.");
        ArrayList<int[]> salidas=new ArrayList<>();
        for(DetalleReceta detalle:receta.getDetalles()){
            int restante=detalle.getCantidad();
            ArrayList<Lote> disponibles=new ArrayList<>();
            for(Lote l:listaLotes)if(l.getIdMedicamento()==detalle.getIdMedicamento() &&
                  l.getCantidad()>0 && !l.getFechaVencimiento().isBefore(LocalDate.now()))disponibles.add(l);
            disponibles.sort((a,b)->a.getFechaVencimiento().compareTo(b.getFechaVencimiento()));
            for(Lote l:disponibles){
                int tomar=Math.min(restante,l.getCantidad());
                if(tomar>0){salidas.add(new int[]{l.getId(),tomar});restante-=tomar;}
                if(restante==0)break;
            }
            if(restante!=0)throw new IllegalArgumentException("Stock insuficiente para el medicamento #"+detalle.getIdMedicamento()+". Ningún lote fue modificado.");
        }
        registrarMovimientosLotes(salidas,idEmpleado,"SALIDA");
        receta.marcarDispensada();
    }
    @Override public ArrayList<Seguro> seguros(){return new ArrayList<>(listaSeguros);}
    @Override public Seguro registrarSeguro(String aseguradora,String tipo,String telefono){
        Validaciones.obligatorio(aseguradora,"Aseguradora");Validaciones.obligatorio(tipo,"Tipo de seguro");
        for(Seguro s:listaSeguros)if(s.getAseguradora().equalsIgnoreCase(aseguradora.trim())&&s.getTipo().equalsIgnoreCase(tipo.trim()))
            throw new IllegalArgumentException("La aseguradora con ese tipo ya existe.");
        Seguro s=new Seguro(sigSeguro++,aseguradora.trim(),tipo.trim(),telefono.trim());listaSeguros.add(s);return s;
    }
    @Override public ArrayList<PolizaPaciente> polizas(){return new ArrayList<>(listaPolizas);}
    @Override public PolizaPaciente registrarPoliza(int idPaciente,int idSeguro,String numero,LocalDate inicio,LocalDate fin){
        boolean paciente=false,seguro=false;
        for(Paciente p:listaPacientes)if(p.getId()==idPaciente)paciente=true;
        for(Seguro s:listaSeguros)if(s.getId()==idSeguro)seguro=true;
        if(!paciente||!seguro)throw new IllegalArgumentException("Paciente o aseguradora no existe.");
        Validaciones.obligatorio(numero,"Número de póliza");
        if(inicio==null||fin==null||fin.isBefore(inicio))throw new IllegalArgumentException("La fecha final debe ser igual o posterior a la inicial.");
        for(PolizaPaciente poliza:listaPolizas)if(poliza.getNumero().equalsIgnoreCase(numero.trim()))throw new IllegalArgumentException("Número de póliza duplicado.");
        PolizaPaciente p=new PolizaPaciente(sigPoliza++,idPaciente,idSeguro,numero.trim(),inicio,fin);listaPolizas.add(p);return p;
    }
    @Override public ArrayList<HorarioMedico> horarios(){return new ArrayList<>(listaHorarios);}
    @Override public HorarioMedico registrarHorario(int idMedico,LocalDate fecha,LocalTime inicio,LocalTime fin,String consultorio){
        boolean medico=false;
        for(Medico m:listaMedicos)if(m.getId()==idMedico)medico=true;
        if(!medico)throw new IllegalArgumentException("Médico no registrado.");
        Validaciones.obligatorio(consultorio,"Consultorio");
        if(fecha==null||inicio==null||fin==null||fecha.isBefore(LocalDate.now())||!inicio.isBefore(fin))
            throw new IllegalArgumentException("Fecha u horas inválidas.");
        for(HorarioMedico h:listaHorarios)if(h.getFecha().equals(fecha) && inicio.isBefore(h.getFin()) && fin.isAfter(h.getInicio()) &&
              (h.getIdMedico()==idMedico||h.getConsultorio().equalsIgnoreCase(consultorio.trim())))
            throw new IllegalArgumentException("Horario superpuesto para el médico o el consultorio.");
        HorarioMedico h=new HorarioMedico(sigHorario++,idMedico,fecha,inicio,fin,consultorio.trim());listaHorarios.add(h);return h;
    }
    @Override public void eliminarHorario(int idHorario){
        for(int i=0;i<listaHorarios.size();i++)if(listaHorarios.get(i).getId()==idHorario){
            HorarioMedico h=listaHorarios.get(i);
            for(Cita c:listaCitas)if(c.getIdMedico()==h.getIdMedico()&&c.getFecha().equals(h.getFecha())&&
                (c.getEstado().equals("Programada")||c.getEstado().equals("Atendida"))&&!c.getHora().isBefore(h.getInicio())&&c.getHora().isBefore(h.getFin()))
                throw new IllegalArgumentException("El horario tiene citas programadas asociadas.");
            listaHorarios.remove(i);return;
        }
        throw new IllegalArgumentException("Horario no encontrado.");
    }

}
