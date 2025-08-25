package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.mapper.SensorMapper;
import pablo.tzeliks.model.Medicao;
import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.model.SensorTemperatura;
import pablo.tzeliks.model.SensorVibracao;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.contracts.*;

import java.util.*;
import java.util.stream.Collectors;

public class SensorService implements SensorInterface<SensorDTO> {

    private final List<Sensor> listaSensores = new ArrayList<>();
    private int proximoId = 1;
    private final SensorFactory factory;

    private final SensorMapper mapperSensor;

    public SensorService() {
        this.mapperSensor = Mappers.getMapper(SensorMapper.class);
        this.factory = new SensorFactory();
    }

    // ---------- CRUD Sensor baseado em DTOs (Métodos Adicionais, achar por ID e Código e Remoção via ID ou Código) ----------

    @Override
    public synchronized void cadastrarSensor(SensorDTO dto) {
        if (dto == null) throw new ServiceException("DTO nulo.");

        // Factory cria a entidade concreta a partir do DTO
        Sensor sensor = SensorFactory.fromDTO(dto);

        if (sensor == null) {
            throw new ServiceException("Erro ao criar sensor a partir do DTO.");
        }

        validarSensor(sensor); // Validações Aplicadas a entidade Sensor

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
        if (listaSensores.isEmpty()) throw new ServiceException("Nenhum sensor foi encontrado.");
        return listaSensores.stream().map(this::toDto).collect(Collectors.toList());
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
        Sensor s = acharPorIdEntidade(id);
        if (s == null) throw new ServiceException("Sensor não encontrado para id: " + id);
        listaSensores.remove(s);
    }

    // ---------- Helpers internos (entidades) ----------

    protected Sensor acharPorCodigoEntidade(Codigo codigo) {
        if (codigo == null) return null;
        return listaSensores.stream().filter(e -> codigo.equals(e.getCodigo())).findFirst().orElse(null);
    }

    protected Sensor acharPorIdEntidade(int id) {
        if (id <= 0) return null;
        return listaSensores.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    // Conversão DTO para entidade de tipo Subclasse de Sensor
    private SensorDTO toDto(Sensor sensor) {
        if (sensor == null) return null;

        if (sensor instanceof SensorTemperatura) {
            return mapperSensor.toDTO((SensorTemperatura) sensor);
        } else if (sensor instanceof SensorVibracao) {
            return mapperSensor.toDTO((SensorVibracao) sensor);
        } else {
            throw new ServiceException("Tipo de equipamento não suportado para mapeamento: " + sensor.getClass().getName());
        }
    }

    // Validação de entidade de Sensor
    private void validarSensor(Sensor sensor) {
        if (sensor == null) throw new ServiceException("Sensor nulo.");
        if (sensor.getNomeEquipamento() == null || sensor.getNomeEquipamento().isBlank()) throw new ServiceException("Nome inválido.");
        if (sensor.getCodigo() == null) throw new ServiceException("Código inválido.");
        if (sensor.getTipo() == null) throw new ServiceException("Tipo inválido.");
    }

    // Validação de entidade de Sensor
    private void validarMedicao(Medicao medicao) {
        if (medicao == null) throw new ServiceException("Medição nula.");
        if (medicao.getSensor() == null) throw new ServiceException("Sensor nulo, sem referência.");
        if (medicao.getValor() < 0) throw new ServiceException("Valor inválido.");
        if (medicao.getDataHoraFormatada() == null || medicao.getDataHoraFormatada().isBlank()) throw new ServiceException("Data/hora inválida.");
    }
}