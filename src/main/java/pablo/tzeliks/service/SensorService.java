package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.mapper.MedicaoMapper;
import pablo.tzeliks.mapper.SensorMapper;
import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.model.SensorTemperatura;
import pablo.tzeliks.model.SensorVibracao;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.contracts.*;

import java.util.*;
import java.util.stream.Collectors;

public class SensorService implements CrudSensoresInterface<SensorDTO>, MedicoesInterface<SensorDTO> {

    private final List<Sensor> listaSensores = new ArrayList<>();
    private int proximoId = 1;

    private final SensorMapper mapperSensor;
    private final MedicaoMapper mapperMedicao;
    private final SensorFactory factory;

    public SensorService() {
        this.mapperSensor = Mappers.getMapper(SensorMapper.class);
        this.mapperMedicao = Mappers.getMapper(MedicaoMapper.class);
        this.factory = new SensorFactory();
    }

    public SensorService(SensorMapper mapperSensor, MedicaoMapper mapperMedicao, SensorFactory factory) {
        this.mapperSensor = Objects.requireNonNull(SensorMapper);
        this.mapperMedicao = Objects.requireNonNull(MedicaoMapper);
        this.factory = Objects.requireNonNull(factory);
    }

    // ---------- CRUD baseado em DTOs ----------

    @Override
    public synchronized void cadastrarSensor(SensorDTO dto) {
        if (dto == null) throw new ServiceException("DTO nulo.");

        // Factory cria a entidade concreta a partir do DTO
        Sensor sensor = SensorFactory.fromDTO(dto);
        if (sensor == null) {
            throw new ServiceException("Erro ao criar sensor a partir do DTO.");
        }

        validarSensor(sensor);

        // Criação de ID quando necessário para os Equipamentos
        if (sensor.getId() <= 0) {
            sensor.setId(proximoId++);
        } else if (sensor.getId() >= proximoId) {
            proximoId = sensor.getId() + 1;
        }

        // Validações adicionais
        if (acharPorCodigoEntidade(sensor.getCodigo()) != null) {
            throw new ServiceException("Já existe um sensor com o mesmo código: " + sensor.getCodigo());
        }
        if (acharPorIdEntidade(sensor.getId()) != null) {
            throw new ServiceException("Já existe um sensor com o mesmo id: " + sensor.getId());
        }

        listaSensores.add(sensor);
    }

    @Override
    public List<SensorDTO> listarSensores() {
        if (listaSensores.isEmpty()) throw new ServiceException("Lista vazia.");
        return listaSensores.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SensorDTO> verificarSensoresCriticos() {
        if (estoque.isEmpty()) throw new ServiceException("Lista vazia.");
        return estoque.stream()
                .filter(e -> e.getTipoEquipamento() == tipoEquipamento)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SensorDTO acharPorCodigo(Codigo codigo) {
        Sensor s = acharPorCodigoEntidade(codigo);
        return s == null ? null : toDto(s);
    }

    @Override
    public SensorDTO acharPorId(int id) {
        Sensor s = acharPorIdEntidade(id);
        return s == null ? null : toDto(s);
    }

    @Override
    public synchronized void removerPorCodigo(Codigo codigo) {
        Sensor s = acharPorCodigoEntidade(codigo);
        if (s == null) throw new ServiceException("Sensor não encontrado para código: " + codigo);
        listaSensores.remove(s);
    }

    @Override
    public synchronized void removerPorId(int id) {
        Equipamento e = acharPorIdEntidade(id);
        if (e == null) throw new ServiceException("Equipamento não encontrado para id: " + id);
        estoque.remove(e);
    }

    // ---------- Helpers internos (entidades) ----------

    private Sensor acharPorCodigoEntidade(Codigo codigo) {
        if (codigo == null) return null;
        return listaSensores.stream().filter(e -> codigo.equals(e.getCodigo())).findFirst().orElse(null);
    }

    private Sensor acharPorIdEntidade(int id) {
        return listaSensores.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    // Conversão DTO para entidade de tipo Subclasse de Sensor
    private SensorDTO toDto(Sensor sensor) {
        if (sensor == null) return null;

        if (sensor instanceof SensorTemperatura) {
            return mapper.toDTO((SensorTemperatura) sensor);
        } else if (sensor instanceof SensorVibracao) {
            return mapper.toDTO((SensorVibracao) sensor);
        } else {
            throw new ServiceException("Tipo de equipamento não suportado para mapeamento: " + sensor.getClass().getName());
        }
    }

    // Validação de entidade (reaproveita lógica)
    private void validarSensor(Sensor sensor) {
        if (sensor == null) throw new ServiceException("Sensor nulo.");
        if (sensor.getNomeEquipamento() == null || sensor.getNomeEquipamento().isBlank()) throw new ServiceException("Nome inválido.");
        if (sensor.getCodigo() == null) throw new ServiceException("Código inválido.");
        if (sensor.getTipo() == null) throw new ServiceException("Tipo inválido.");
    }
}