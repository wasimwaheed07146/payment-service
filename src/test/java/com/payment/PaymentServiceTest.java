package com.payment;

import com.payment.domain.PaymentMethodEntity;
import com.payment.domain.PaymentPlanEntity;
import com.payment.dto.PaymentMethodDto;
import com.payment.dto.PaymentPlanDto;
import com.payment.dto.PaymentResponseDto;
import com.payment.enums.Currency;
import com.payment.enums.PaymentType;
import com.payment.exception.ApplicationErrorType;
import com.payment.exception.PaymentException;
import com.payment.mappers.PaymentMethodMapper;
import com.payment.repository.PaymentMethodRepository;
import com.payment.service.impl.PaymentMethodService;
import com.payment.specs.PaymentMethodSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Wasim Waheed
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;
    @Mock
    private PaymentMethodMapper paymentMethodMapper;
    @InjectMocks
    private PaymentMethodService paymentMethodService;

    @Test
    public void testSaveMethodIfMethodNameAlreadyExist(){
        PaymentMethodDto paymentMethodDto = PaymentMethodDto.builder().name("voucher").build();

        Mockito.when(paymentMethodRepository.existsByName(paymentMethodDto.getName())).thenReturn(Boolean.TRUE);

        PaymentException ex = Assertions.assertThrows(PaymentException.class, ()-> paymentMethodService.save(paymentMethodDto));
        assertEquals(ApplicationErrorType.PAYMENT_METHOD_NAME_ALREADY_EXISTS.getErrorCode(), ex.getAppCode());
    }

    @Test
    public void testSaveMethodWhenPaymentPlanNotExistForSuccess(){
        PaymentMethodDto paymentMethodDto = getPaymentMethodDtoWithoutPlans();

        PaymentMethodEntity paymentMethodEntity = getPaymentMethodEntityWithoutPlans();

        Mockito.when(paymentMethodMapper.mapToEntity(paymentMethodDto)).thenReturn(paymentMethodEntity);
        Mockito.when(paymentMethodRepository.save(paymentMethodEntity)).thenReturn(paymentMethodEntity);
        Mockito.when(paymentMethodMapper.mapToDto(paymentMethodEntity)).thenReturn(paymentMethodDto);

        PaymentResponseDto response = paymentMethodService.save(paymentMethodDto);
        Mockito.verify(paymentMethodRepository, Mockito.times(1)).save(paymentMethodEntity);

        assertThat(response.getPaymentMethods()).hasSize(1);
        assertEquals(paymentMethodEntity.getPaymentType(),response.getPaymentMethods().get(0).getPaymentType());
    }

    @Test
    public void testSaveMethodWhenPaymentPlanExistsForSuccess(){

        PaymentMethodDto paymentMethodDto = getPaymentMethodDto();

        PaymentMethodEntity paymentMethodEntity = getPaymentMethodEntity();
        Mockito.when(paymentMethodMapper.mapToEntity(paymentMethodDto)).thenReturn(paymentMethodEntity);
        Mockito.when(paymentMethodRepository.save(paymentMethodEntity)).thenReturn(paymentMethodEntity);
        Mockito.when(paymentMethodMapper.mapToDto(paymentMethodEntity)).thenReturn(paymentMethodDto);

        PaymentResponseDto response = paymentMethodService.save(paymentMethodDto);

        Mockito.verify(paymentMethodRepository, Mockito.times(1)).save(paymentMethodEntity);

        assertThat(response.getPaymentMethods()).hasSize(1);
        assertEquals(paymentMethodEntity.getPaymentType(),response.getPaymentMethods().get(0).getPaymentType());
        assertThat(response.getPaymentMethods().get(0).getPaymentPlans()).hasSize(1);
        assertEquals(paymentMethodEntity.getPaymentPlans().get(0).getCurrency(),response.getPaymentMethods().get(0).getPaymentPlans().get(0).getCurrency());
    }

    @Test
    public void testFindAllMethodWhenEmptyListReturn(){
        Mockito.when(paymentMethodRepository.findAll(Mockito.any(PaymentMethodSpecification.class))).thenReturn(Collections.emptyList());
        Mockito.when(paymentMethodMapper.mapToDto(Mockito.anyList())).thenReturn(Collections.emptyList());

        PaymentResponseDto response = paymentMethodService.findAll(null,null);

        Mockito.verify(paymentMethodRepository, Mockito.times(1)).findAll(Mockito.any(PaymentMethodSpecification.class));
        assertThat(response.getPaymentMethods()).hasSize(0);
    }

    @Test
    public void testFindAllMethodWhenNameFilterIsPresentForSuccess(){

        List<PaymentMethodEntity> paymentMethodEntityList = Arrays.asList(getPaymentMethodEntity());
        List<PaymentMethodDto> paymentMethodDtoList = Arrays.asList(getPaymentMethodDto());

        Mockito.when(paymentMethodRepository.findAll(Mockito.any(PaymentMethodSpecification.class))).thenReturn(paymentMethodEntityList);
        Mockito.when(paymentMethodMapper.mapToDto(paymentMethodEntityList)).thenReturn(paymentMethodDtoList);

        PaymentResponseDto response = paymentMethodService.findAll(null,"voucher");

        Mockito.verify(paymentMethodRepository, Mockito.times(1)).findAll(Mockito.any(PaymentMethodSpecification.class));

        assertThat(response.getPaymentMethods()).hasSize(1);
        assertEquals(paymentMethodEntityList.get(0).getPaymentType(),response.getPaymentMethods().get(0).getPaymentType());
        assertThat(response.getPaymentMethods().get(0).getPaymentPlans()).hasSize(1);
        assertEquals(paymentMethodEntityList.get(0).getPaymentPlans().get(0).getCurrency(),response.getPaymentMethods().get(0).getPaymentPlans().get(0).getCurrency());
    }

    @Test
    public void testFindAllMethodWhenIdFilterIsPresentForSuccess(){

        List<PaymentMethodEntity> paymentMethodEntityList = Arrays.asList(getPaymentMethodEntity());
        List<PaymentMethodDto> paymentMethodDtoList = Arrays.asList(getPaymentMethodDto());

        Mockito.when(paymentMethodRepository.findAll(Mockito.any(PaymentMethodSpecification.class))).thenReturn(paymentMethodEntityList);
        Mockito.when(paymentMethodMapper.mapToDto(paymentMethodEntityList)).thenReturn(paymentMethodDtoList);

        PaymentResponseDto response = paymentMethodService.findAll(1,"voucher");

        Mockito.verify(paymentMethodRepository, Mockito.times(1)).findAll(Mockito.any(PaymentMethodSpecification.class));

        assertThat(response.getPaymentMethods()).hasSize(1);
        assertEquals(paymentMethodEntityList.get(0).getPaymentType(),response.getPaymentMethods().get(0).getPaymentType());
        assertThat(response.getPaymentMethods().get(0).getPaymentPlans()).hasSize(1);
        assertEquals(paymentMethodEntityList.get(0).getPaymentPlans().get(0).getId(),response.getPaymentMethods().get(0).getPaymentPlans().get(0).getId());
    }

    @Test
    public void testUpdateMethodWhenRecordNotExist(){
        Integer paymentMethodId = null;
        PaymentException ex = Assertions.assertThrows(PaymentException.class, ()-> paymentMethodService.updatePaymentMethod(paymentMethodId,new PaymentMethodDto()));
        assertEquals(ApplicationErrorType.PAYMENT_METHOD_NOT_FOUND.getErrorCode(), ex.getAppCode());
    }

    @Test
    public void testUpdatePaymentMethodForSuccess(){
        Integer paymentMethodId = 1;
        PaymentMethodDto updatedDto = getPaymentMethodDto();
        updatedDto.setPaymentType(PaymentType.CREDIT_CARD);

        PaymentMethodEntity existingPaymentMethodEntity = getPaymentMethodEntity();
        Mockito.when(paymentMethodRepository.findById(paymentMethodId)).thenReturn(Optional.of(existingPaymentMethodEntity));

        PaymentMethodEntity updatedEntity = getPaymentMethodEntity();
        updatedEntity.setPaymentType(PaymentType.CREDIT_CARD);
        Mockito.when(paymentMethodMapper.updatePaymentMethod(existingPaymentMethodEntity,updatedDto)).thenReturn(updatedEntity);
        Mockito.when(paymentMethodRepository.save(updatedEntity)).thenReturn(updatedEntity);
        Mockito.when(paymentMethodMapper.mapToDto(updatedEntity)).thenReturn(updatedDto);

        PaymentResponseDto response = paymentMethodService.updatePaymentMethod(paymentMethodId,updatedDto);

        Mockito.verify(paymentMethodRepository, Mockito.times(1)).save(updatedEntity);

        assertThat(response.getPaymentMethods()).hasSize(1);
        assertEquals(updatedDto.getPaymentType(),response.getPaymentMethods().get(0).getPaymentType());
        assertThat(response.getPaymentMethods().get(0).getPaymentPlans()).hasSize(1);
    }



    private PaymentMethodDto getPaymentMethodDtoWithoutPlans(){
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setName("voucher");
        paymentMethodDto.setPaymentType(PaymentType.VOUCHER);
        return paymentMethodDto;
    }

    private PaymentMethodEntity getPaymentMethodEntityWithoutPlans(){
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(1);
        paymentMethodEntity.setName("voucher");
        paymentMethodEntity.setPaymentType(PaymentType.VOUCHER);
        paymentMethodEntity.setPaymentPlans(Collections.emptyList());
        return paymentMethodEntity;
    }

    private PaymentMethodDto getPaymentMethodDto(){
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setName("voucher");
        paymentMethodDto.setPaymentType(PaymentType.VOUCHER);
        paymentMethodDto.setPaymentPlans(getListOfPaymentPlanDto());
        return paymentMethodDto;
    }

    private PaymentMethodEntity getPaymentMethodEntity(){
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(1);
        paymentMethodEntity.setName("voucher");
        paymentMethodEntity.setPaymentType(PaymentType.VOUCHER);
        paymentMethodEntity.setPaymentPlans(getListOfPaymentPlanEntity());
        return paymentMethodEntity;
    }

    private List<PaymentPlanDto> getListOfPaymentPlanDto(){
        PaymentPlanDto paymentPlanDto = new PaymentPlanDto();
        paymentPlanDto.setId(1);
        paymentPlanDto.setCurrency(Currency.USD);
        paymentPlanDto.setNetAmount(new BigDecimal(10.00));
        return Arrays.asList(paymentPlanDto);
    }

    private List<PaymentPlanEntity> getListOfPaymentPlanEntity(){
        PaymentPlanEntity paymentPlanEntity= new PaymentPlanEntity();
        paymentPlanEntity.setId(1);
        paymentPlanEntity.setCurrency(Currency.USD);
        paymentPlanEntity.setNetAmount(new BigDecimal(10.00));
        return Arrays.asList(paymentPlanEntity);
    }


}
