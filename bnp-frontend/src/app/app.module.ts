import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MovimentoManualComponent} from './movimento-manual/movimento-manual.component';
import {CoreModule} from "../../../../../Workspace-Ck/gap/gap-frontend/src/app/core/core.module";

@NgModule({
  declarations: [
    AppComponent,
    MovimentoManualComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
