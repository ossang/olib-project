import { NgModule }                   from '@angular/core';
import { CommonModule }               from '@angular/common';
import { BsDropdownModule }           from 'ngx-bootstrap/dropdown';

import { LayoutRoutingModule }        from './layout-routing.module';
import { LayoutComponent }            from './layout.component';

import { HeaderComponent, SidebarComponent } from '../../shared';
import { NAV_DROPDOWN_DIRECTIVES }    from '../../shared/directives/nav-dropdown.directive';
import { SIDEBAR_TOGGLE_DIRECTIVES }  from '../../shared/directives/sidebar.directive';

import { FeedMenuComponent }          from '../rss/feed-menu/feed-menu.component';
import { RssMenuComponent }           from '../rss/rss-menu/rss-menu.component';

import { BookmarkService }            from '../rss/bookmark/bookmark.service';
import { RssService }                 from '../rss/rss.service';

@NgModule({
  imports: [
    CommonModule,
    BsDropdownModule.forRoot(),
    LayoutRoutingModule
  ],
  declarations: [
    LayoutComponent,
    HeaderComponent,
    SIDEBAR_TOGGLE_DIRECTIVES,
    NAV_DROPDOWN_DIRECTIVES,
    SidebarComponent,
    FeedMenuComponent,
    RssMenuComponent 
  ],
  providers : [ 
    BookmarkService,
    RssService
  ]
})
export class LayoutModule { }
