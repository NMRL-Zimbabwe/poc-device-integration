import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CentralRepositorySharedModule } from 'app/shared/shared.module';
import { IdServerComponent } from './id-server.component';
import { IdServerDetailComponent } from './id-server-detail.component';
import { IdServerUpdateComponent } from './id-server-update.component';
import { IdServerDeleteDialogComponent } from './id-server-delete-dialog.component';
import { idServerRoute } from './id-server.route';

@NgModule({
  imports: [CentralRepositorySharedModule, RouterModule.forChild(idServerRoute)],
  declarations: [IdServerComponent, IdServerDetailComponent, IdServerUpdateComponent, IdServerDeleteDialogComponent],
  entryComponents: [IdServerDeleteDialogComponent],
})
export class CentralRepositoryIdServerModule {}
