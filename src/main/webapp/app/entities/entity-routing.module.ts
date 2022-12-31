import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        data: { pageTitle: 'pocDeviceIntegrationApp.client.home.title' },
        loadChildren: () => import('./client/client.module').then(m => m.ClientModule),
      },
      {
        path: 'patient',
        data: { pageTitle: 'pocDeviceIntegrationApp.patient.home.title' },
        loadChildren: () => import('./patient/patient.module').then(m => m.PatientModule),
      },
      {
        path: 'sample',
        data: { pageTitle: 'pocDeviceIntegrationApp.sample.home.title' },
        loadChildren: () => import('./sample/sample.module').then(m => m.SampleModule),
      },
      {
        path: 'sample-type',
        data: { pageTitle: 'pocDeviceIntegrationApp.sampleType.home.title' },
        loadChildren: () => import('./sample-type/sample-type.module').then(m => m.SampleTypeModule),
      },
      {
        path: 'analysis-service',
        data: { pageTitle: 'pocDeviceIntegrationApp.analysisService.home.title' },
        loadChildren: () => import('./analysis-service/analysis-service.module').then(m => m.AnalysisServiceModule),
      },
      {
        path: 'sample-patient',
        data: { pageTitle: 'pocDeviceIntegrationApp.samplePatient.home.title' },
        loadChildren: () => import('./sample-patient/sample-patient.module').then(m => m.SamplePatientModule),
      },
      {
        path: 'id-server',
        data: { pageTitle: 'pocDeviceIntegrationApp.samplePatient.home.title' },
        loadChildren: () => import('./id-server/id-server.module').then(m => m.IdServerModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
