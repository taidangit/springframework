import { Directive, ElementRef, Renderer, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appMenu]'
})
export class MenuDirective {

	@Input("appMenu") highLightColor: string;
	@Input("defaultColor") defaultColor: string = "limegreen";

  	constructor(private el: ElementRef, private renderer: Renderer) { }

	@HostListener('mouseenter') onMouseEnter(){
		this.highlight(this.highLightColor || this.defaultColor);
	}

	@HostListener('mouseleave') onMouseLeave(){
		this.highlight(null);
	}

	private highlight(color: string) {
		this.renderer.setElementStyle(this.el.nativeElement, 'backgroundColor', color);
		this.renderer.setElementStyle(this.el.nativeElement, 'cursor', 'hand');
	}

}
