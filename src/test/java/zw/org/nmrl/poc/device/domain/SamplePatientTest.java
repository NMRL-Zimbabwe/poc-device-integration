package zw.org.nmrl.poc.device.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import zw.org.nmrl.poc.device.web.rest.TestUtil;

class SamplePatientTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SamplePatient.class);
        SamplePatient samplePatient1 = new SamplePatient();
        samplePatient1.setId(1L);
        SamplePatient samplePatient2 = new SamplePatient();
        samplePatient2.setId(samplePatient1.getId());
        assertThat(samplePatient1).isEqualTo(samplePatient2);
        samplePatient2.setId(2L);
        assertThat(samplePatient1).isNotEqualTo(samplePatient2);
        samplePatient1.setId(null);
        assertThat(samplePatient1).isNotEqualTo(samplePatient2);
    }
}
