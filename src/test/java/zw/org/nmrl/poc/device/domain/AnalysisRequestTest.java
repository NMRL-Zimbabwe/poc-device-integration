package zw.org.nmrl.poc.device.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import zw.org.nmrl.poc.device.web.rest.TestUtil;

class AnalysisRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnalysisRequest.class);
        AnalysisRequest sample1 = new AnalysisRequest();
		/*
		 * sample1.setId(1L); Sample sample2 = new Sample();
		 * sample2.setId(sample1.getId()); assertThat(sample1).isEqualTo(sample2);
		 * sample2.setId(2L); assertThat(sample1).isNotEqualTo(sample2);
		 * sample1.setId(null); assertThat(sample1).isNotEqualTo(sample2);
		 */
    }
}
