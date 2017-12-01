import { NgModule }             from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LayoutComponent }      from './layout.component';

const routes: Routes = [
    {
        path: '', component: LayoutComponent,
        children: [
            { path: 'rss/:id', loadChildren: '../rss/rss.module#RssModule' },
            { path: 'option',  loadChildren: '../rss/rss-option/rss-option.module#RssOptionModule'}
        ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
