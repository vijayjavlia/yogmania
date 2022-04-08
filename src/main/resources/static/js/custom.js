$('.slider-big.slide-one .owl-carousel').owlCarousel({
    loop:true,
    margin:5,
    dots: false,
    responsiveClass:true,
    responsive:{
        0:{
            items:1,
            nav:true
        },
        600:{
            items:1,
            nav:false
        },
        1000:{
            items:1,
            loop:false
        }
    }
});
$('.slider-big .owl-carousel').owlCarousel({
    loop:true,
    margin:5,
    dots: false,
    responsiveClass:true,
    responsive:{
        0:{
            items:1,
            nav:true
        },
        600:{
            items:1,
            nav:false
        },
        1000:{
            items:3,
            nav:true,
            loop:false
        }
    }
});
$('.catslider .owl-carousel').owlCarousel({
    loop:false,
    margin:15,
    dots: false,
    responsiveClass:true,
    responsive:{
        0:{
            items:2.1,
            nav:false
            
        },
        600:{
            items:3.1,
            nav:false,
            center:false
        },
        1000:{
            items:5.1,
            rtl: false,
            nav:false
        }
    }
})