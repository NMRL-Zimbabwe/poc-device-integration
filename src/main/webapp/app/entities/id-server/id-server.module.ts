import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { IdServerComponent } from './list/id-server.component';
import { IdServerDetailComponent } from './detail/id-server-detail.component';
import { IdServerUpdateComponent } from './update/id-server-update.component';
import { IdServerDeleteDialogComponent } from './delete/sample-type-delete-dialog.component';
import { IdServerRoutingModule } from './route/id-server-routing.module';

@NgModule({
  imports: [SharedModule, IdServerRoutingModule],
  declarations: [IdServerComponent, IdServerDetailComponent, IdServerUpdateComponent, IdServerDeleteDialogComponent],
})
export class IdServerModule {}
